package rbekyarov.car_dealership.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import rbekyarov.car_dealership.models.dto.SaleDTO;
import rbekyarov.car_dealership.models.entity.*;
import rbekyarov.car_dealership.models.entity.Currency;
import rbekyarov.car_dealership.models.entity.enums.StatusSalesInvoiced;
import rbekyarov.car_dealership.repository.CostRepository;
import rbekyarov.car_dealership.repository.SaleRepository;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.services.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static rbekyarov.car_dealership.services.CommonService.getUserEntity;


@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;
    private final ClientService clientService;
    private final SellerService sellerService;
    private final OfferService offerService;
    private final CompanyService companyService;
    private final CurrencyService currencyService;
    private final CostRepository costRepository;
    private final UserRepository userRepository;


    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper, CarService carService, ClientService clientService, SellerService sellerService, OfferService offerService, CompanyService companyService, CurrencyService currencyService, CostRepository costRepository, UserRepository userRepository) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.carService = carService;
        this.clientService = clientService;
        this.sellerService = sellerService;
        this.offerService = offerService;
        this.companyService = companyService;
        this.currencyService = currencyService;
        this.costRepository = costRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Sale> findAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public void addSale(SaleDTO saleDTO) {
        Sale sale = new Sale();
        Set<Long> carIds = saleDTO.getCarIds();
        //Add Car in SALE
        Set<Car> carSet = carService.addCarInOfferAndSale(carIds);
        sale.setCars(carSet);
        //SET CURRENCY
        Currency currency = currencyService.findMainCurrency();
        sale.setCurrency(currency);
        //SET PRICE SALE
        BigDecimal price = carService.calculatePriceOnCars(carIds);
        sale.setPrice(price);

        //CALCULATE AND SET TOTAL PRICE AND DISCOUNT SALE
        calculateAndSetTotalPriceAndDiscount(saleDTO, sale, price);

        // SET STATUS SALE - for new SALE  - always will be 'NO'
        sale.setStatusSalesInvoiced(StatusSalesInvoiced.NO);

        //get and set Client
        sale.setClient(clientService.findById(saleDTO.getClientId()).orElseThrow());

        //get and set Seller
        sale.setSeller(sellerService.findById(saleDTO.getSellerId()).orElseThrow());

        //get and set Company
        sale.setCompany(companyService.findById(saleDTO.getCompanyId()).orElseThrow());

        //get and set Author
//        UserEntity user = getUserEntity();
//        sale.setAuthor(user);
        sale.setAuthor(userRepository.getUsersById(1L));

        // set dateCreated
        sale.setDateCreate(LocalDate.now());

        saleRepository.save(sale);
    }

    @Override
    public void removeSaleById(Long id) {
        saleRepository.deleteById(id);
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return saleRepository.findById(id);
    }

    @Override
    public void editSale(SaleDTO saleDTO, Long id) {
        Set<Long> carIds = saleDTO.getCarIds();

        //CALC PRICE SALE
        BigDecimal price = carService.calculatePriceOnCars(carIds);

        //CALCULATE AND GET TOTAL PRICE AND DISCOUNT SALE
        Map<BigDecimal, BigDecimal> priceAndDiscount = new LinkedHashMap<>();
        priceAndDiscount = calculateAndGetTotalPriceAndDiscount(saleDTO, price);
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal discount = new BigDecimal(0);
        for (Map.Entry<BigDecimal, BigDecimal> entry : priceAndDiscount.entrySet()) {
            totalPrice = totalPrice.add(entry.getKey());
            discount = discount.add(entry.getValue());
        }

        // GET STATUS SALE
        StatusSalesInvoiced saleDTOStatusSalesInvoiced = saleDTO.getStatusSalesInvoiced();

        //get Client
        Long clientId = saleDTO.getClientId();

        //get Seller
        Long sellerId = saleDTO.getSellerId();

        //get Company
        Long companyId = saleDTO.getCompanyId();

//        UserEntity user = getUserEntity();
//        Long editUserId = user.getId();
        Long editUserId = 1L;
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        //Clear
        List<Car> carList = carService.findAllCarsOnThisOfferId(id);
        carService.clearValueOfferIdsOnThisCars(carList);

        saleRepository.editSale(price, totalPrice, discount, saleDTOStatusSalesInvoiced, clientId, sellerId, id, editUserId, dateEdit,companyId);

        //Change
        Set<Car> cars = carService.addCarInOfferAndSale(carIds);
        carService.updateCarOfferIdFields(cars, id);

    }

    @Override
    public void updateStatusInvoicedToYes(Long saleId) {
        saleRepository.updateStatusInvoiced(saleId);
    }

    @Override
    public void transformOfferToSale(Long offerId) {
        Offer offer = offerService.findById(offerId).get();
        Sale sale = modelMapper.map(offer,Sale.class);
        sale.setStatusSalesInvoiced(StatusSalesInvoiced.NO);
//        UserEntity user = getUserEntity();
//        sale.setAuthor(user);
        sale.setAuthor(userRepository.getUsersById(1L));
        sale.setDateCreate(LocalDate.now());
        sale.setId(null);
        saleRepository.save(sale);
    }

    private static void calculateAndSetTotalPriceAndDiscount(SaleDTO saleDTO, Sale sale, BigDecimal price) {
        BigDecimal discountPercent = new BigDecimal(0);

        if (saleDTO.getDiscount() == null) {
            discountPercent = new BigDecimal(0);
        }
        BigDecimal percent = new BigDecimal("100");
        discountPercent = saleDTO.getDiscount();
        BigDecimal discountP = discountPercent.divide(percent);
        BigDecimal discount = price.multiply(discountP);
        BigDecimal totalPrice = price.subtract(discount);
        sale.setDiscount(discount);
        sale.setTotalPrice(totalPrice);
    }

    private Map<BigDecimal, BigDecimal> calculateAndGetTotalPriceAndDiscount(SaleDTO saleDTO, BigDecimal price) {
        BigDecimal discountPercent = new BigDecimal(0);

        if (saleDTO.getDiscount() == null) {
            discountPercent = new BigDecimal(0);
        }
        BigDecimal percent = new BigDecimal("100");
        discountPercent = saleDTO.getDiscount();
        BigDecimal discountP = discountPercent.divide(percent);
        BigDecimal discount = price.multiply(discountP);
        BigDecimal totalPrice = price.subtract(discount);
        Map<BigDecimal, BigDecimal> result = new LinkedHashMap<>();
        result.put(totalPrice, discount);
        return result;
    }
}

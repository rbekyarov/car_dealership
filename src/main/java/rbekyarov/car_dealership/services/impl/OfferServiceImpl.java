package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.OfferDTO;
import rbekyarov.car_dealership.models.entity.Car;
import rbekyarov.car_dealership.models.entity.Offer;
import rbekyarov.car_dealership.models.entity.enums.StatusOffer;
import rbekyarov.car_dealership.repository.CostRepository;
import rbekyarov.car_dealership.repository.OfferRepository;
import rbekyarov.car_dealership.services.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CarService carService;
    private final ClientService clientService;
    private final SellerService sellerService;
    private final CostRepository costRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, UserService userService, CarService carService, ClientService clientService, SellerService sellerService,
                            CostRepository costRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.carService = carService;
        this.clientService = clientService;
        this.sellerService = sellerService;
        this.costRepository = costRepository;
    }

    @Override
    public List<Offer> findAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public void addOffer(OfferDTO offerDTO, HttpSession session) {
        Offer offer = new Offer();
        Set<Long> carIds = offerDTO.getCarIds();
        //Add Car in Offer
        Set<Car> carSet = carService.addCarInOfferAndSale(carIds);
        offer.setCars(carSet);

        //SET PRICE OFFER
        BigDecimal price = carService.calculatePriceOnCars(carIds);
        offer.setPrice(price);

        //CALCULATE AND SET TOTAL PRICE AND DISCOUNT OFFER
        calculateAndSetTotalPriceAndDiscount(offerDTO, offer, price);

        // SET STATUS OFFER - for new offer  - always will be 'proposed'
        offer.setStatusOffer(StatusOffer.proposed);

        //get and set Client
        offer.setClient(clientService.findById(offerDTO.getClientId()).orElseThrow());

        //get and set Seller
        offer.setSeller(sellerService.findById(offerDTO.getSellerId()).orElseThrow());

        //get and set Author
        //offer.setAuthor(userService.getAuthorFromSession(session));
        offer.setAuthor(userService.findById(1L).get());

        // set dateCreated
        offer.setDateCreate(LocalDate.now());


        offerRepository.save(offer);


    }

    @Override
    public void removeOfferById(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public Optional<Offer> findById(Long id) {
        return offerRepository.findById(id);
    }

    @Override
    public void editOffer(OfferDTO offerDTO, Long id, HttpSession session) {

        Set<Long> carIds = offerDTO.getCarIds();

        //CALC PRICE OFFER
        BigDecimal price = carService.calculatePriceOnCars(carIds);


        //CALCULATE AND GET TOTAL PRICE AND DISCOUNT OFFER
        Map<BigDecimal, BigDecimal> priceAndDiscount = new LinkedHashMap<>();
        priceAndDiscount = calculateAndGetTotalPriceAndDiscount(offerDTO, price);
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal discount = new BigDecimal(0);
        for (Map.Entry<BigDecimal, BigDecimal> entry : priceAndDiscount.entrySet()) {
            totalPrice = totalPrice.add(entry.getKey());
            discount = discount.add(entry.getValue());
        }

        // GET STATUS OFFER
        StatusOffer statusOffer = offerDTO.getStatusOffer();

        //get Client
        Long clientId = offerDTO.getClientId();

        //get Seller
        Long sellerId = offerDTO.getSellerId();


         //User editUser = userService.getAuthorFromSession(session);
         //Long editUserId = editUser.getId();
         Long editUserId = 1L;

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        //Clear
        List<Car> carList = carService.findAllCarsOnThisOfferId(id);
        carService.clearValueOfferIdsOnThisCars(carList);

        offerRepository.editOffer(price, totalPrice, discount, statusOffer, clientId, sellerId, id, editUserId, dateEdit);

        //Change
        Set<Car> cars = carService.addCarInOfferAndSale(carIds);
        carService.updateCarOfferIdFields(cars, id);


    }

    private Map<BigDecimal, BigDecimal> calculateAndGetTotalPriceAndDiscount(OfferDTO offerDTO, BigDecimal price) {
        BigDecimal discountPercent = new BigDecimal(0);

        if (offerDTO.getDiscount() == null) {
            discountPercent = new BigDecimal(0);
        }
        BigDecimal percent = new BigDecimal("100");
        discountPercent = offerDTO.getDiscount();
        BigDecimal discountP = discountPercent.divide(percent);
        BigDecimal discount = price.multiply(discountP);
        BigDecimal totalPrice = price.subtract(discount);
        Map<BigDecimal, BigDecimal> result = new LinkedHashMap<>();
        result.put(totalPrice, discount);
        return result;
    }

    private static void calculateAndSetTotalPriceAndDiscount(OfferDTO offerDTO, Offer offer, BigDecimal price) {
        BigDecimal discountPercent = new BigDecimal(0);

        if (offerDTO.getDiscount() == null) {
            discountPercent = new BigDecimal(0);
        }
        BigDecimal percent = new BigDecimal("100");
        discountPercent = offerDTO.getDiscount();
        BigDecimal discountP = discountPercent.divide(percent);
        BigDecimal discount = price.multiply(discountP);
        BigDecimal totalPrice = price.subtract(discount);
        offer.setDiscount(discount);
        offer.setTotalPrice(totalPrice);
    }
}

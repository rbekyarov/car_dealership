package rbekyarov.car_dealership;

import jakarta.servlet.http.HttpSession;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rbekyarov.car_dealership.models.dto.*;
import rbekyarov.car_dealership.models.entity.*;
import rbekyarov.car_dealership.models.entity.enums.*;
import rbekyarov.car_dealership.repository.*;
import rbekyarov.car_dealership.services.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestCrudCar implements CommandLineRunner {
   private final PictureService pictureService;
   private final CarService carService;
   private final HttpSession httpSession;
   private final UserRepository userRepository;
   private final BrandRepository brandRepository;
   private final ModelRepository modelRepository;
   private final VendorRepository vendorRepository;
   private final PricingPercentDataRepository pricingPercentDataRepository;
    private final CostRepository costRepository;
    private final CostService costService;
    private final OfferService offerService;
    private final SaleService saleService;
    private final CompanyService companyService;
    private final ClientService clientService;
    private final SellerService sellerService;
    private final InvoiceService invoiceService;
    private final BankAccountService bankAccountService;
    private final CurrencyService currencyService;


    public TestCrudCar(PictureService pictureService, CarService carService, HttpSession httpSession, UserRepository userRepository, BrandRepository brandRepository, ModelRepository modelRepository, VendorRepository vendorRepository, PricingPercentDataRepository pricingPercentDataRepository,
                       CostRepository costRepository, CostService costService, OfferService offerService, SaleService saleService, CompanyService companyService, ClientService clientService, SellerService sellerService, InvoiceService invoiceService, BankAccountService bankAccountService, CurrencyService currencyService) {
        this.pictureService = pictureService;
        this.carService = carService;
        this.httpSession = httpSession;
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.vendorRepository = vendorRepository;
        this.pricingPercentDataRepository = pricingPercentDataRepository;
        this.costRepository = costRepository;
        this.costService = costService;
        this.offerService = offerService;
        this.saleService = saleService;
        this.companyService = companyService;
        this.clientService = clientService;
        this.sellerService = sellerService;
        this.invoiceService = invoiceService;
        this.bankAccountService = bankAccountService;
        this.currencyService = currencyService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("rado");
        user.setRole(Role.ADMIN);
        user.setPassword("123");
        user.setEmail("rb@abv.bg");
        userRepository.save(user);



        PictureDTO pictureDTO = new PictureDTO();


        //създавам бранд
        brandRepository.save(new Brand("AUDI"));
        //създавам model
        modelRepository.save(new Model("Q5", brandRepository.findById(1L).get()));
        //Създаване на снимка
        Set<Picture> pictureSet = new HashSet<>();
        Picture picture = new Picture();
        picture.setName("pic1");
        Picture picture1 = new Picture();
        picture1.setName("pic2");
        pictureSet.add(picture);
        pictureSet.add(picture1);

        Set<Picture> pictureSet2 = new HashSet<>();
        Picture picture5 = new Picture();
        picture5.setName("pic5");
        Picture picture6 = new Picture();
        picture6.setName("pic6");
        pictureSet.add(picture5);
        pictureSet.add(picture6);

        // създаване на Принцинг
        PricingPercentData pricingPercentData = new PricingPercentData(50,30,5, ActivePricingPercentData.YES,20);
        pricingPercentDataRepository.save(pricingPercentData);
        //TEST ADD CURRENCY
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCode("EURO");
        currencyDTO.setName("EURO");
        currencyDTO.setExchangeRate(0.85);
        currencyDTO.setIsMainCurrency(IsMainCurrency.YES);
        currencyService.addCurrency(currencyDTO,httpSession);

        CurrencyDTO currencyDTO1 = new CurrencyDTO();
        currencyDTO1.setCode("USD");
        currencyDTO1.setName("Dollar");
        currencyDTO1.setExchangeRate(1.18);
        currencyDTO1.setIsMainCurrency(IsMainCurrency.NO);
        currencyService.addCurrency(currencyDTO1,httpSession);



        CarDTO carDTO = new CarDTO();

        carDTO.setModelId(1L);
        Vendor vendor = new Vendor("AEG", "VFBAEHFJA");
        vendorRepository.save(vendor);
        carDTO.setVinNumber("5344GFEAFKBBCGG4");
        carDTO.setPricePurchase(new BigDecimal("10000"));
        carDTO.setVendorId(1L);
        carDTO.setAirbags(Airbags.YES);
        carService.addCar(carDTO,pictureSet,httpSession);


        CarDTO carDTO1 = new CarDTO();

        carDTO1.setModelId(1L);
        Vendor vendor1 = new Vendor("BBB", "BG524534244");
        vendorRepository.save(vendor1);
        carDTO1.setVinNumber("OUAEOUFHUEAHFO");
        carDTO1.setPricePurchase(new BigDecimal("5000"));
        carDTO1.setVendorId(2L);
        carDTO1.setAirbags(Airbags.YES);
        carService.addCar(carDTO1,pictureSet2,httpSession);

        //test edit car
        Set<Picture> pictureSetEdite = new HashSet<>();
        Picture picture3 = new Picture();
        picture3.setName("pic3");
        Picture picture4 = new Picture();
        picture4.setName("pic4");
        pictureSetEdite.add(picture3);
        pictureSetEdite.add(picture4);

        CarDTO carEditDTO = new CarDTO();

        carEditDTO.setModelId(1L);
        Vendor vendorEdit = new Vendor("AEGEdite", "VFBAEHFJAxxx");
        vendorRepository.save(vendorEdit);
        carEditDTO.setVinNumber("EDITE5344GFEAFKBBCGG4");
        carEditDTO.setPricePurchase(new BigDecimal("12000"));
        carEditDTO.setVendorId(2L);
        carEditDTO.setAirbags(Airbags.NO);
        carEditDTO.setCategory(Category.jeep);
        carService.editCar(carEditDTO,pictureSetEdite,1L,httpSession);


        //carService.removeCarById(1L);
        //test add Cost for Car
        CostDTO costDTO = new CostDTO();
        costDTO.setCarId(1L);
        costDTO.setVendorId(1L);
        costDTO.setAmount(new BigDecimal(3000));
        costDTO.setDescription("Transport Car");
        costService.addCost(costDTO,httpSession);

        //Test Edit Cost
        costService.editCost(2L,2l,null,null,new BigDecimal (400), LocalDate.now(), 1L,httpSession);

        CostDTO costDTO2 = new CostDTO();
        costDTO2.setCarId(2L);
        costDTO2.setVendorId(1L);
        costDTO2.setAmount(new BigDecimal(100));
        costDTO2.setDescription("Clean Car");
        costService.addCost(costDTO2,httpSession);



        //costService.removeCostById(1L);

        //ADD COMPANY
        CompanyDTO companyDTO = new CompanyDTO("MegaCar","Bulgaria", "Stara Zagora", "Simeon Veliki 1A", "BG534454545", "megacar@gmail.com", "Anton Petrov");
        companyService.addCompany(companyDTO,httpSession);

        //Test ADD OFFER
        OfferDTO offerDTO = new OfferDTO();
        Set<Long>carIds = new HashSet<>();
        carIds.add(1L);
        carIds.add(2L);
        offerDTO.setCarIds(carIds);
        offerDTO.setStatusOffer(StatusOffer.proposed);
        offerDTO.setDiscount(new BigDecimal(10));
        offerDTO.setCompanyId(1L);
        //add client
        clientService.addClient(new ClientDTO("Clien1"), httpSession);
        offerDTO.setClientId(1L);
        //add Seller
        sellerService.addSeller(new SellerDTO("Spas"), httpSession);
        offerDTO.setSellerId(1L);
        offerService.addOffer(offerDTO,httpSession);


        //Test Edit OFFER
        OfferDTO offerEditDTO = new OfferDTO();
        Set<Long>carEditIds = new HashSet<>();
        carEditIds.add(1L);
        offerEditDTO.setCarIds(carEditIds);
        offerEditDTO.setStatusOffer(StatusOffer.rejected);
        offerEditDTO.setDiscount(new BigDecimal(10));
        clientService.addClient(new ClientDTO("Clien2"), httpSession);
        offerEditDTO.setClientId(2L);
        sellerService.addSeller(new SellerDTO("Angel"), httpSession);
        offerEditDTO.setSellerId(2L);
        offerService.editOffer(offerEditDTO,1L,httpSession);


        //Test ADD SALE
        SaleDTO saleDTO = new SaleDTO();
        Set<Long>carIdsSale = new HashSet<>();
        carIdsSale.add(1L);
        carIdsSale.add(2L);
        saleDTO.setCarIds(carIdsSale);
        saleDTO.setDiscount(new BigDecimal(20));
        saleDTO.setCompanyId(1L);
        //add client
        clientService.addClient(new ClientDTO("ClientSale"), httpSession);
        saleDTO.setClientId(1L);
        saleDTO.setCompanyId(1L);
        //add Seller
        sellerService.addSeller(new SellerDTO("SpasSale"), httpSession);
        saleDTO.setSellerId(1L);
        saleService.addSale(saleDTO,httpSession);

        //Test Edit SALE
        SaleDTO saleEditDTO = new SaleDTO();
        Set<Long>carEditIds1 = new HashSet<>();
        carEditIds1.add(1L);
        saleEditDTO.setCarIds(carEditIds);

        saleEditDTO.setStatusSalesInvoiced(StatusSalesInvoiced.YES);
        saleEditDTO.setDiscount(new BigDecimal(10));
        clientService.addClient(new ClientDTO("Clien2"), httpSession);
        saleEditDTO.setClientId(2L);
        saleEditDTO.setCompanyId(1L);
        sellerService.addSeller(new SellerDTO("Angel"), httpSession);
        saleEditDTO.setSellerId(2L);
        saleService.editSale(saleEditDTO,1L,httpSession);
        //TEST ADD BANK-ACCOUNT

        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setBankName("Bulbank AD");
        bankAccountDTO.setName("Bulbank EURO");
        bankAccountDTO.setCurrencyId(1L);
        bankAccountDTO.setCompanyId(1L);
        bankAccountDTO.setAccountNumber("UCREURO54543EAF543555");
        bankAccountDTO.setBalance(new BigDecimal(0.0));
        bankAccountService.addBankAccount(bankAccountDTO,httpSession);

        BankAccountDTO bankAccountDTO1 = new BankAccountDTO();
        bankAccountDTO1.setBankName("Bulbank AD");
        bankAccountDTO1.setName("Bulbank USD");
        bankAccountDTO1.setCurrencyId(2L);
        bankAccountDTO1.setCompanyId(1L);
        bankAccountDTO1.setAccountNumber("UCRUSD54543EAF543555");
        bankAccountDTO1.setBalance(new BigDecimal(0.0));
        bankAccountService.addBankAccount(bankAccountDTO1,httpSession);

        //TEST ADD INVOICE FROM SALE

        invoiceService.addInvoice(1l,httpSession);

        //TEST CANCELLED INVOICE

       // invoiceService.cancellationInvoiceById(1l,httpSession);

        //TEST TRANSFORM OFFER TO SALE
        saleService.transformOfferToSale(1L,httpSession);

        System.out.println("aa");

    }
}

package rbekyarov.car_dealership.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.entity.*;
import rbekyarov.car_dealership.models.entity.enums.CancellationInvoice;
import rbekyarov.car_dealership.repository.InvoiceRepository;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.services.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static rbekyarov.car_dealership.services.CommonService.getUserEntity;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;
    private final BankAccountService bankAccountService;
    private final PricingPercentDataService pricingPercentDataService;
    private final SellerService sellerService;
    private final CarService carService;
    private final SaleService saleService;
    private final UserRepository userRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ModelMapper modelMapper, BankAccountService bankAccountService, PricingPercentDataService pricingPercentDataService, SellerService sellerService, CarService carService, SaleService saleService, UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
        this.bankAccountService = bankAccountService;
        this.pricingPercentDataService = pricingPercentDataService;
        this.sellerService = sellerService;
        this.carService = carService;
        this.saleService = saleService;
        this.userRepository = userRepository;
    }


    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> findAllRealInvoice() {
        return invoiceRepository.findAllRealInvoice();
    }

    @Override
    public List<Invoice> findAllCancelledInvoice() {
        return invoiceRepository.findAllCancelledInvoice();
    }

    @Override
    public void addInvoice (Long saleId) {
        Sale sale = saleService.findById(saleId).orElseThrow();
        //Invoice invoice = modelMapper.map(sale,Invoice.class);

        Invoice invoice = new Invoice();
        invoice.setPrice(sale.getPrice());
        invoice.setTotalPrice(sale.getTotalPrice());
        invoice.setSaleId(sale.getId());
        invoice.setDiscount(sale.getDiscount().doubleValue());

        invoice.setCompanyCityName(sale.getCompany().getCity());
        invoice.setCompanyAddress(sale.getCompany().getAddress());
        invoice.setCompanyVatNumber(sale.getCompany().getVatNumber());
        invoice.setCompanyManagerName(sale.getCompany().getManagerName());
        invoice.setCompanyEmail(sale.getCompany().getEmail());
        invoice.setCompanyName(sale.getCompany().getName());

        invoice.setClientAddress(sale.getClient().getCity());
        invoice.setClientEmail(sale.getClient().getCity());
        invoice.setClientCityName(sale.getClient().getCity());
        invoice.setClientPhone(sale.getClient().getPhone());
        invoice.setClientName(sale.getClient().getCity());

        //SET CARS
        Set<Car> cars = sale.getCars();
        Set <CarInvoiced> carInvoiced = new HashSet<>();
        for (Car car : cars) {
            CarInvoiced carInvoiced1 = new CarInvoiced();
            carInvoiced1.setCarName(car.getName());
            carInvoiced1.setCarVinNumber(car.getVinNumber());
            carInvoiced1.setCarRegDate(car.getRegDate());
            carInvoiced.add(carInvoiced1);
        }
        //SET BANK-ACCOUNT: ID,NUMBER,NAME;
        Set<BankAccount> bankAccounts = sale.getCompany().getBankAccounts();

        for (BankAccount bankAccount : bankAccounts) {
            String name = sale.getCurrency().getName();
            String name1 = bankAccount.getCurrency().getName();

            if (Objects.equals(sale.getCurrency().getName(), bankAccount.getCurrency().getName())){
               invoice.setBankAccountId(bankAccount.getId());
               invoice.setCompanyBankAccount(bankAccount.getAccountNumber());
               invoice.setCompanyBankName(bankAccount.getBankName());

           }
        }
        //SET COMPANY CITY NAME
        invoice.setCompanyCityName(sale.getCompany().getCity());
        //
        invoice.setCarInvoiced(carInvoiced);
        // SET CURRENCY CODE
        invoice.setCurrencyCode(sale.getCurrency().getCode());
        //SET Cancellation Invoice
        invoice.setCancellationInvoice(CancellationInvoice.NO);
        // set dateCreated
        invoice.setDateCreate(LocalDate.now());
        //get and set Author
       // UserEntity user = getUserEntity();

        //invoice.setAuthorName(user.getFirstName()+" " +user.getLastName());
        invoice.setAuthorName("HARDCODED");

        invoiceRepository.save(invoice);
        //SET CAR STATUS AVAILABLE
        for (Car car : cars) {
           carService.updateStatusAvailableSold(car.getId());
        }
        //SET DATE_SOLD IN CAR TABLE
        for (Car car : cars) {
            LocalDate dateSold = LocalDate.now();
            carService.updateDateSold(dateSold,car.getId());
        }

        //SET BALANCE
        BigDecimal totalPrice = sale.getTotalPrice();
        BankAccount bankAccount = bankAccountService.findById(invoice.getBankAccountId()).get();
        BigDecimal balance = bankAccount.getBalance();
        balance = balance.add(totalPrice);
        bankAccountService.updateBalance(balance,bankAccount.getId());

        //CALCULATE AND SET PROFIT
        for (Car car : cars) {
            BigDecimal priceSale = car.getPriceSale();
            BigDecimal pricePurchase = car.getPricePurchase();
            BigDecimal profit = priceSale.subtract(pricePurchase);
            carService.updateProfitForCar(profit, car.getId());
            car.setPriceProfit(profit);
        }

        //CALCULATE AND SET COMMISSION - % from Profit
        PricingPercentData pricingPercentData = pricingPercentDataService.findActivePricingPercentData().get();
        int percentCommission = pricingPercentData.getPercentCommission();
        for (Car car : cars) {
            BigDecimal priceProfit = car.getPriceProfit();
            BigDecimal percentCommission100 = new BigDecimal(percentCommission /100.0) ;
            BigDecimal commission = priceProfit.multiply(percentCommission100);
            carService.updateCommissionForCar(commission, car.getId());
            car.setPriceCommission(commission);
        }
        // ADD COMMISSION THE SELLER
        Long sellerId = sale.getSeller().getId();
        for (Car car : cars) {
            BigDecimal priceCommission = car.getPriceCommission();
            Seller seller = sellerService.findById(sellerId).get();
            BigDecimal totalProfit = seller.getTotalProfit();
            totalProfit = totalProfit.add(priceCommission);
            sellerService.addCommission(totalProfit,sellerId);
        }

        //Change Sale Status to Invoiced
        saleService.updateStatusInvoicedToYes(saleId);

        // set dateCreated
        invoice.setCancelledDateInvoice(LocalDate.now());


    }


    @Override
    public void cancellationInvoiceById(Long id) {

        Invoice invoice = invoiceRepository.findById(id).get();
        //SET INVOICE STATUS CANCELLATION
        invoiceRepository.setCanceledOnInvoiced(invoice.getId());

        Sale sale = saleService.findById(invoice.getSaleId()).get();
        Set<Car> cars = sale.getCars();
        //SET CAR STATUS AVAILABLE
        for (Car car : cars) {
            carService.updateStatusAvailableAvailable(car.getId());
        }
        //SET DATE_SOLD IN CAR TABLE
        for (Car car : cars) {
            LocalDate dateSold = null;
            carService.updateDateSold(dateSold,car.getId());
        }

        //UPDATE BALANCE
        BigDecimal totalPrice = invoice.getTotalPrice();
        BankAccount bankAccount = bankAccountService.findById(invoice.getBankAccountId()).get();
        BigDecimal balance = bankAccount.getBalance();
        balance = balance.subtract(totalPrice);
        bankAccountService.updateBalance(balance,bankAccount.getId());

        // reset PROFIT
        for (Car car : cars) {
            carService.updateProfitForCar(new BigDecimal(0), car.getId());

        }
        // reset COMMISSION
        for (Car car : cars) {
            carService.updateCommissionForCar(new BigDecimal(0), car.getId());

        }

        // subtraction COMMISSION THE SELLER
        Long sellerId = sale.getSeller().getId();
        for (Car car : cars) {
            BigDecimal priceCommission = car.getPriceCommission();
            Seller seller = sellerService.findById(sellerId).get();
            BigDecimal totalProfit = seller.getTotalProfit();
            totalProfit = totalProfit.subtract(priceCommission);
            sellerService.addCommission(totalProfit,sellerId);
        }

        //get and set Cancellation User Name
//        UserEntity user = getUserEntity();
//
//        invoiceRepository.setCancellationUserName(user.getFirstName()+" " +user.getLastName(),id);
       invoiceRepository.setCancellationUserName("HARDCODED",id);

        LocalDate canceledDate = LocalDate.now();
        invoiceRepository.setDateCancelation(canceledDate,id);
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public List<Invoice> listInvoiceById(Long invoiceNumber) {
        return invoiceRepository.listInvoiceById(invoiceNumber);
    }

    @Override
    public List<Invoice> listInvoiceByEmail(String clientEmail) {
        return invoiceRepository.listInvoiceByEmail(clientEmail);
    }

    @Override
    public BigDecimal getTotalInvoicedPrice() {
        List<Invoice> allRealInvoice = invoiceRepository.findAllRealInvoice();
        BigDecimal totalAmount = new BigDecimal("0");
        for (Invoice invoice : allRealInvoice) {
            totalAmount = totalAmount.add(invoice.getTotalPrice());
        }
        return totalAmount;
    }

    @Override
    public List<Invoice> findLastInvoices() {
        return null;
    }
}

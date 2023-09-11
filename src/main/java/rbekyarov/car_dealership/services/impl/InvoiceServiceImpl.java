package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.entity.*;
import rbekyarov.car_dealership.models.entity.enums.CancellationInvoice;
import rbekyarov.car_dealership.repository.InvoiceRepository;
import rbekyarov.car_dealership.repository.SaleRepository;
import rbekyarov.car_dealership.services.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final BankAccountService bankAccountService;
    private final PricingPercentDataService pricingPercentDataService;
    private final SellerService sellerService;
    private final CarService carService;
    private final SaleRepository saleRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ModelMapper modelMapper, UserService userService, BankAccountService bankAccountService
            , PricingPercentDataService pricingPercentDataService, SellerService sellerService, CarService carService, SaleRepository saleRepository) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.bankAccountService = bankAccountService;
        this.pricingPercentDataService = pricingPercentDataService;
        this.sellerService = sellerService;
        this.carService = carService;
        this.saleRepository = saleRepository;
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
    public void addInvoice (Long saleId, HttpSession session) {
        Sale sale = saleRepository.findById(saleId).orElseThrow();
        Invoice invoice = modelMapper.map(sale,Invoice.class);
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
        //invoice.setAuthorName(userService.getAuthorFromSession(session).getUsername());
        invoice.setAuthorName(userService.findById(1L).orElseThrow().getUsername());

        invoiceRepository.save(invoice);

        //SET BALANCE
        BigDecimal totalPrice = invoice.getTotalPrice();
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
    }


    @Override
    public void cancellationInvoiceById(Long id) {
        //change BankAccount Balance
        Optional<Invoice> invoiceById = invoiceRepository.findById(id);
        Invoice invoice = invoiceById.get();
        BigDecimal currentBalance = bankAccountService.getCurrentBalance(invoice.getBankAccountId());
        BigDecimal totalPrice = invoice.getTotalPrice();
        bankAccountService.editBalance(currentBalance.subtract(totalPrice), invoice.getBankAccountId());
        //change Salle Invoiced Status on "NO"
        //reservationService.changeInvoicedStatus(invoice.getReservationId(), Invoiced.NO);
        //invoice.setCancelledDateInvoice(LocalDate.now());

        //set Invoice - cancellation
        invoiceRepository.setCanceledOnInvoiced(invoice.getId());
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

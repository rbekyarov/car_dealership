package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.InvoiceDTO;
import rbekyarov.car_dealership.models.entity.Car;
import rbekyarov.car_dealership.models.entity.CarInvoiced;
import rbekyarov.car_dealership.models.entity.Invoice;
import rbekyarov.car_dealership.models.entity.Sale;
import rbekyarov.car_dealership.models.entity.enums.CancellationInvoice;
import rbekyarov.car_dealership.repository.InvoiceRepository;
import rbekyarov.car_dealership.repository.SaleRepository;
import rbekyarov.car_dealership.services.BankAccountService;
import rbekyarov.car_dealership.services.InvoiceService;
import rbekyarov.car_dealership.services.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final BankAccountService bankAccountService;
    private final SaleRepository saleRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ModelMapper modelMapper, UserService userService, BankAccountService bankAccountService,
                              SaleRepository saleRepository) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.bankAccountService = bankAccountService;
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
        //SET BANKACCOUNT ID
        //invoice.setBankAccountId(sale.getCompany().getBankAccounts().);
        //SET BANKACCOUNT NAME
        //invoice.setCompanyBankName(sale.getCompany().get
        //
        invoice.setCarInvoiced(carInvoiced);
        // SET CURRENCY CODE
        invoice.setCurrencyCode(sale.getCurrency().getCode());
        //SET Cancellation Invoice
        invoice.setCancellationInvoice(CancellationInvoice.NO);
        //SEt COMPANY CITYNAME
        invoice.setCompanyCityName(sale.getCompany().getName());
        // set dateCreated
        invoice.setDateCreate(LocalDate.now());
        //get and set Author
        //invoice.setAuthorName(userService.getAuthorFromSession(session).getUsername());
        invoice.setAuthorName(userService.findById(1L).orElseThrow().getUsername());

        invoiceRepository.save(invoice);
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

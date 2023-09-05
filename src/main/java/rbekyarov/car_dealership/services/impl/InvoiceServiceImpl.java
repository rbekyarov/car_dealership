package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.InvoiceDTO;
import rbekyarov.car_dealership.models.entity.Invoice;
import rbekyarov.car_dealership.repository.InvoiceRepository;
import rbekyarov.car_dealership.services.BankAccountService;
import rbekyarov.car_dealership.services.InvoiceService;
import rbekyarov.car_dealership.services.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final BankAccountService bankAccountService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ModelMapper modelMapper, UserService userService, BankAccountService bankAccountService) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.bankAccountService = bankAccountService;
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
    public void addInvoice(InvoiceDTO invoiceDTO, HttpSession session) {
             ///?????//
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

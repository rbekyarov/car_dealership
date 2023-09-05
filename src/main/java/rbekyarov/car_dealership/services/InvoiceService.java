package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.dto.InvoiceDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Invoice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    List<Invoice> findAllInvoice();
    List<Invoice> findAllRealInvoice();
    List<Invoice> findAllCancelledInvoice();

    void addInvoice(InvoiceDTO invoiceDTO, HttpSession session);

    void cancellationInvoiceById(Long id);

    Optional<Invoice> findById(Long id);

    List<Invoice> listInvoiceById(Long invoiceNumber);

    List<Invoice> listInvoiceByEmail(String clientEmail);


    BigDecimal getTotalInvoicedPrice();

    List<Invoice> findLastInvoices();

}

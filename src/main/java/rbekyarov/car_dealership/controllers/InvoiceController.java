package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.dto.OfferDTO;
import rbekyarov.car_dealership.models.entity.Invoice;
import rbekyarov.car_dealership.models.entity.Offer;
import rbekyarov.car_dealership.services.InvoiceService;
import rbekyarov.car_dealership.services.OfferService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<?> getAllInvoices() {
        List<Invoice> invoices = invoiceService.findAllInvoice();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invoice not exist with id: " + id));
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> generateInvoice(@PathVariable Long id, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            invoiceService.addInvoice(id, session);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> cancellationInvoice(@PathVariable Long id, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {

            invoiceService.cancellationInvoiceById(id, session);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


}

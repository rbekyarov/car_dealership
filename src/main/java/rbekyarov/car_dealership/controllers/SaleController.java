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
import rbekyarov.car_dealership.models.dto.SaleDTO;
import rbekyarov.car_dealership.models.entity.Offer;
import rbekyarov.car_dealership.models.entity.Sale;
import rbekyarov.car_dealership.services.OfferService;
import rbekyarov.car_dealership.services.SaleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<?> getAllSales() {
        List<Sale> allSales = saleService.findAllSales();
        return new ResponseEntity<>(allSales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable Long id) {
        Sale sale = saleService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sale not exist with id: " + id));
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSale(@RequestBody @Valid SaleDTO saleDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            saleService.addSale(saleDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }
    @PostMapping("/{id}")
    public ResponseEntity<?> transformOfferToSale(@PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            saleService.transformOfferToSale(id);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSale(@PathVariable Long id, @RequestBody @Valid SaleDTO saleDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {

            saleService.editSale(saleDTO,id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSale(@PathVariable Long id) {
        saleService.removeSaleById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

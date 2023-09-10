package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.dto.CostDTO;
import rbekyarov.car_dealership.models.entity.Cost;
import rbekyarov.car_dealership.services.CostService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/costs")
public class CostController {

    @Autowired
    private CostService costService;

    @GetMapping
    public ResponseEntity<?> getAllCosts() {
        List<Cost> allCosts = costService.findAllCosts();
        return new ResponseEntity<>(allCosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCostById(@PathVariable Long id) {
        Cost cost = costService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vendor not exist with id: " + id));
        return new ResponseEntity<>(cost, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCost(@RequestBody @Valid CostDTO costDTO, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            costService.addCost(costDTO, session);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCost(@PathVariable Long id, @RequestBody @Valid CostDTO costDTO, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            Long vendorId = costDTO.getVendorId();
            Long carId = costDTO.getCarId();
            String description = costDTO.getDescription();
            String invoiceNo = costDTO.getInvoiceNo();
            BigDecimal amount = costDTO.getAmount();
            LocalDate dateCost = costDTO.getDateCost();

            costService.editCost(vendorId, carId, description, invoiceNo, amount, dateCost, id, session);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCost(@PathVariable Long id) {
        costService.removeCostById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

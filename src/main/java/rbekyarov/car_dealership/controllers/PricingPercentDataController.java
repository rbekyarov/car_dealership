package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.dto.PricingPercentDataDTO;
import rbekyarov.car_dealership.models.entity.PricingPercentData;
import rbekyarov.car_dealership.models.entity.enums.ActivePricingPercentData;
import rbekyarov.car_dealership.services.PricingPercentDataService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pricing_percent_dates")
public class PricingPercentDataController {

    @Autowired
    private PricingPercentDataService pricingPercentDataService;

    @GetMapping
    public ResponseEntity<?> getAllPricingPercentDates() {
        List<PricingPercentData> allPricingPercentDates = pricingPercentDataService.findAllPricingPercentDates();
        return new ResponseEntity<>(allPricingPercentDates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPricingPercentDataById(@PathVariable Long id) {
        PricingPercentData pricingPercentData = pricingPercentDataService.findById(id).orElseThrow(() -> new ResourceNotFoundException("pricingPercentData not exist with id: " + id));
        return new ResponseEntity<>(pricingPercentData, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPricingPercentData(@RequestBody @Valid PricingPercentDataDTO pricingPercentDataDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            pricingPercentDataService.addPricingPercentData(pricingPercentDataDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePricingPercentData(@PathVariable Long id, @RequestBody @Valid PricingPercentDataDTO pricingPercentDataDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            int percentSaleCar = pricingPercentDataDTO.getPercentSaleCar();
            int percentSaleCarMin = pricingPercentDataDTO.getPercentSaleCarMin();
            int percentCommission = pricingPercentDataDTO.getPercentCommission();
            ActivePricingPercentData activePricingPercentData = pricingPercentDataDTO.getActivePricingPercentData();
            int percentVAT = pricingPercentDataDTO.getPercentVAT();


            pricingPercentDataService.editPricingPercentData(percentSaleCar, percentSaleCarMin, percentCommission, activePricingPercentData, percentVAT, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePricingPercentData(@PathVariable Long id) {
        pricingPercentDataService.removePricingPercentDataById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

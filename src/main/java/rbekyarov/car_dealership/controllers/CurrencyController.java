package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.dto.CurrencyDTO;
import rbekyarov.car_dealership.models.entity.Currency;
import rbekyarov.car_dealership.services.CurrencyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<?> getAllCurrencies() {
        List<Currency> allCurrencies = currencyService.findAllCurrencies();
        return new ResponseEntity<>(allCurrencies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCurrencydById(@PathVariable Long id) {
        Currency currency = currencyService.findById(id).orElseThrow(() -> new ResourceNotFoundException("currency not exist with id: " + id));
        return new ResponseEntity<>(currency, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCurrency(@RequestBody @Valid CurrencyDTO currencyDTO, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            currencyService.addCurrency(currencyDTO, session);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurrency(@PathVariable Long id, @RequestBody @Valid CurrencyDTO currencyDTO, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            currencyService.editCurrency(currencyDTO, id, session);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCurrency(@PathVariable Long id) {
        currencyService.removeCurrencyById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

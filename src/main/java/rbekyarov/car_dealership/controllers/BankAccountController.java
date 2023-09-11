package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.dto.BankAccountDTO;
import rbekyarov.car_dealership.models.dto.CarDTO;
import rbekyarov.car_dealership.models.entity.BankAccount;
import rbekyarov.car_dealership.models.entity.Car;
import rbekyarov.car_dealership.models.entity.Picture;
import rbekyarov.car_dealership.services.BankAccountService;
import rbekyarov.car_dealership.services.CarService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public ResponseEntity<?> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountService.findAllBankAccounts();
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBankAccountById(@PathVariable Long id) {
        BankAccount bankAccount = bankAccountService.findById(id).orElseThrow(() -> new ResourceNotFoundException("BankAccount not exist with id: " + id));
        return new ResponseEntity<>(bankAccount, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createBankAccount(@RequestBody @Valid BankAccountDTO bankAccountDTO, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            bankAccountService.addBankAccount(bankAccountDTO, session);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBankAccount(@PathVariable Long id, @RequestBody @Valid BankAccountDTO bankAccountDTO, Set<Picture> pictures, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            String name = bankAccountDTO.getName();
            String bankName = bankAccountDTO.getBankName();
            String accountNumber = bankAccountDTO.getAccountNumber();
            Long currencyId = bankAccountDTO.getCurrencyId();
            BigDecimal balance = bankAccountDTO.getBalance();
            bankAccountService.editBankAccount(name, bankName, accountNumber, currencyId, balance, id, session);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCar(@PathVariable Long id) {
        bankAccountService.removeBankAccountById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

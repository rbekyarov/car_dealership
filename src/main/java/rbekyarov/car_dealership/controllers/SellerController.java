package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.dto.SellerDTO;
import rbekyarov.car_dealership.models.entity.Seller;
import rbekyarov.car_dealership.models.entity.enums.Position;
import rbekyarov.car_dealership.services.SellerService;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping
    public ResponseEntity<?> getAllSellers() {
        List<Seller> allSellers = sellerService.findAllSellers();
        return new ResponseEntity<>(allSellers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSellerById(@PathVariable Long id) {
        Seller seller = sellerService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seller not exist with id: " + id));
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSeller(@RequestBody @Valid SellerDTO sellerDTO,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            sellerService.addSeller(sellerDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSeller(@PathVariable Long id, @RequestBody @Valid SellerDTO sellerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            String firstName = sellerDTO.getFirstName();
            String lastName = sellerDTO.getLastName();
            Position position = sellerDTO.getPosition();
            BigDecimal salary = sellerDTO.getSalary();

            sellerService.editSeller(firstName, lastName, position, salary, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSeller(@PathVariable Long id) {
        sellerService.removeSellerById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

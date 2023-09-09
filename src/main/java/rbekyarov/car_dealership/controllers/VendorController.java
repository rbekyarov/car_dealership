package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.dto.VendorDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Vendor;
import rbekyarov.car_dealership.services.BrandService;
import rbekyarov.car_dealership.services.VendorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping
    public ResponseEntity<?>getAllVendors() {
        List<Vendor> allVendors = vendorService.findAllVendors();
        return new ResponseEntity<>(allVendors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorById(@PathVariable Long id) {
        Vendor vendor = vendorService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vendor not exist with id: " + id));
        return new ResponseEntity<>(vendor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createVendor(@RequestBody @Valid VendorDTO vendorDTO, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            vendorService.addVendor(vendorDTO,session);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateVendor(@PathVariable Long id, @RequestBody @Valid VendorDTO vendorDTO, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            String address = vendorDTO.getAddress();
            String city = vendorDTO.getCity();
            String name = vendorDTO.getName();
            String country = vendorDTO.getCountry();
            String email = vendorDTO.getEmail();
            String vatNumber = vendorDTO.getVatNumber();

            vendorService.editVendor( name, country, city, address, vatNumber, email,  id,  session);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBrand(@PathVariable Long id) {
        vendorService.removeVendorById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.dto.VendorDTO;
import rbekyarov.car_dealership.models.entity.Vendor;
import rbekyarov.car_dealership.security.jwt.JwtUtils;
import rbekyarov.car_dealership.services.VendorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private VendorService vendorService;

    @GetMapping
    public ResponseEntity<?> getAllVendors() {
        List<Vendor> allVendors = vendorService.findAllVendors();
        return new ResponseEntity<>(allVendors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorById(@PathVariable Long id) {
        Vendor vendor = vendorService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vendor not exist with id: " + id));
        return new ResponseEntity<>(vendor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createVendor(@RequestHeader("Authorization") String authorizationHeader, @RequestBody @Valid VendorDTO vendorDTO, BindingResult bindingResult) {

        String token = extractTokenFromAuthorizationHeader(authorizationHeader);

        if (token != null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(getUsernameFromToken(token));

            if (userDetails != null) {
                if (jwtUtils.validateJwtToken(token)) {

                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();


                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    if (bindingResult.hasErrors()) {
                        Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
                        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
                    } else {
                        vendorService.addVendor(vendorDTO);
                        return new ResponseEntity<>(HttpStatus.CREATED);
                    }
                }
            }
        }

        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    private String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    private String getUsernameFromToken(String token) {
        return jwtUtils.getUserNameFromJwtToken(token);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateVendor(@PathVariable Long id, @RequestBody @Valid VendorDTO vendorDTO, BindingResult bindingResult) {
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

            vendorService.editVendor(name, country, city, address, vatNumber, email, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteVendor(@PathVariable Long id) {
        vendorService.removeVendorById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }


}

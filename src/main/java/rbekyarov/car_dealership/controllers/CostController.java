package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
import rbekyarov.car_dealership.models.dto.CostDTO;
import rbekyarov.car_dealership.models.entity.Cost;
import rbekyarov.car_dealership.security.jwt.JwtUtils;
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
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
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
    public ResponseEntity<?> createCost(@RequestHeader("Authorization") String authorizationHeader,@RequestBody @Valid CostDTO costDTO, BindingResult bindingResult) {
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
                        costService.addCost(costDTO);
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
    public ResponseEntity<?> updateCost(@PathVariable Long id, @RequestBody @Valid CostDTO costDTO, BindingResult bindingResult) {
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

            costService.editCost(vendorId, carId, description, invoiceNo, amount, dateCost, id);
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

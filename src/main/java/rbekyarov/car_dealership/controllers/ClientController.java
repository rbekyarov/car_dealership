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
import rbekyarov.car_dealership.models.dto.ClientDTO;
import rbekyarov.car_dealership.models.entity.Client;
import rbekyarov.car_dealership.models.entity.enums.ClientType;
import rbekyarov.car_dealership.security.jwt.JwtUtils;
import rbekyarov.car_dealership.services.ClientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        List<Client> allClients = clientService.findAllClients();
        return new ResponseEntity<>(allClients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Client client = clientService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not exist with id: " + id));
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestHeader("Authorization") String authorizationHeader,@RequestBody @Valid ClientDTO clientDTO, BindingResult bindingResult) {
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
                        clientService.addClient(clientDTO);
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
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody @Valid ClientDTO clientDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            String name = clientDTO.getName();
            String vatOrId = clientDTO.getVatOrId();
            String email = clientDTO.getEmail();
            String phone = clientDTO.getPhone();
            String city = clientDTO.getCity();
            String address = clientDTO.getAddress();
            ClientType clientType = clientDTO.getClientType();

            clientService.editClient(name, email, vatOrId, phone, city, address, clientType, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClient(@PathVariable Long id) {
        clientService.removeClientById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

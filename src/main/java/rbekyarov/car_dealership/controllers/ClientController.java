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
import rbekyarov.car_dealership.models.dto.ClientDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Client;
import rbekyarov.car_dealership.models.entity.enums.ClientType;
import rbekyarov.car_dealership.services.BrandService;
import rbekyarov.car_dealership.services.ClientService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

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
    public ResponseEntity<?> createClient(@RequestBody @Valid ClientDTO clientDTO, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            clientService.addClient(clientDTO,session);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateClient(@PathVariable Long id, @RequestBody @Valid ClientDTO clientDTO, HttpSession session, BindingResult bindingResult) {
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

            clientService.editClient(name, email, vatOrId, phone, city, address, clientType, id, session);
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

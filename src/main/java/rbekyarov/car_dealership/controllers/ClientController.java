package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.dto.ClientDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Client;
import rbekyarov.car_dealership.models.entity.enums.ClientType;
import rbekyarov.car_dealership.services.BrandService;
import rbekyarov.car_dealership.services.ClientService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.findAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.findById(id).orElseThrow();
    }

    @PostMapping
    void createClient(@RequestBody ClientDTO clientDTO, HttpSession session) {
        clientService.addClient(clientDTO, session);
    }

    @PutMapping("/{id}")
    void updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO, HttpSession session) {

        String name = clientDTO.getName();
        String vatOrId = clientDTO.getVatOrId();
        String email = clientDTO.getEmail();
        String phone = clientDTO.getPhone();
        String city = clientDTO.getCity();
        String address = clientDTO.getAddress();
        ClientType clientType = clientDTO.getClientType();

        clientService.editClient(name, email, vatOrId, phone, city, address, clientType, id, session);

    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.removeClientById(id);
    }
}

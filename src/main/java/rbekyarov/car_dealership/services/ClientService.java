package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.ClientDTO;
import rbekyarov.car_dealership.models.entity.Client;
import rbekyarov.car_dealership.models.entity.enums.ClientType;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAllClients();

    void addClient(ClientDTO clientDTO, HttpSession session);

    void removeClientById(Long id);

    Optional<Client> findById(Long id);

    void editClient(String name,String vatOrId,String email,String phone,String city ,String address,ClientType clientType, Long id, HttpSession session);

}

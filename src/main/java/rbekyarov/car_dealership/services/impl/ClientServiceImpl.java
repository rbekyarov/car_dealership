package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.ClientDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Client;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.models.entity.enums.ClientType;
import rbekyarov.car_dealership.repository.BrandRepository;
import rbekyarov.car_dealership.repository.ClientRepository;
import rbekyarov.car_dealership.services.ClientService;
import rbekyarov.car_dealership.services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ModelMapper modelMapper, UserService userService,
                             ClientRepository clientRepository) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void addClient(ClientDTO clientDTO, HttpSession session) {
        Client client = modelMapper.map(clientDTO, Client.class);
        //get and set Author
        client.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        client.setDateCreate(LocalDate.now());
        clientRepository.save(client);
    }

    @Override
    public void removeClientById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void editClient(String name, String vatOrId, String email, String phone, String city, String address, ClientType clientType, Long id, HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        clientRepository.editClient(name, vatOrId, email, phone, city, address, clientType, id, editAuthorId, dateEdit);
    }
}

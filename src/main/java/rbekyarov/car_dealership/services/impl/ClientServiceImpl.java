package rbekyarov.car_dealership.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.ClientDTO;
import rbekyarov.car_dealership.models.entity.Client;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.models.entity.enums.ClientType;
import rbekyarov.car_dealership.repository.ClientRepository;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.services.ClientService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static rbekyarov.car_dealership.services.CommonService.getUserEntity;

@Service
public class ClientServiceImpl implements ClientService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ModelMapper modelMapper, UserRepository userRepository, ClientRepository clientRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void addClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        //get and set Author
//        UserEntity user = getUserEntity();
//        client.setAuthor(user);
        client.setAuthor(userRepository.getUsersById(1L));
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
    public void editClient(String name, String vatOrId, String email, String phone, String city, String address, ClientType clientType, Long id) {
//        UserEntity user = getUserEntity();
//        Long editUserId = user.getId();
        Long editUserId = 1L;
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        clientRepository.editClient(name, vatOrId, email, phone, city, address, clientType, id, editUserId, dateEdit);
    }
}

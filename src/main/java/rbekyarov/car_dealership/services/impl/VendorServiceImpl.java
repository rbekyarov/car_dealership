package rbekyarov.car_dealership.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.VendorDTO;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.models.entity.Vendor;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.repository.VendorRepository;
import rbekyarov.car_dealership.services.VendorService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static rbekyarov.car_dealership.services.CommonService.getUserEntity;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public VendorServiceImpl(VendorRepository vendorRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.vendorRepository = vendorRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public List<Vendor> findAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public void addVendor(VendorDTO vendorDTO) {
        Vendor vendor = modelMapper.map(vendorDTO, Vendor.class);
        //get and set Author
//        UserEntity user = getUserEntity();
//        vendor.setAuthor(user);
        vendor.setAuthor(userRepository.getUsersById(1L));
        // set dateCreated
        vendor.setDateCreate(LocalDate.now());
        vendorRepository.save(vendor);
    }

    @Override
    public void removeVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public Optional<Vendor> findById(Long id) {
        return vendorRepository.findById(id);
    }

    @Override
    public void editVendor(String name, String country, String city, String address, String vatNumber, String email, Long id) {
//        UserEntity user = getUserEntity();
//        Long editUserId = user.getId();
        Long editUserId = 1L;
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        vendorRepository.editVendor(name,country,city,address,vatNumber,email, id,editUserId, dateEdit);
    }
}

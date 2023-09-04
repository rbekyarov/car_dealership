package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.dto.VendorDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.models.entity.Vendor;
import rbekyarov.car_dealership.repository.VendorRepository;
import rbekyarov.car_dealership.services.UserService;
import rbekyarov.car_dealership.services.VendorService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public VendorServiceImpl(VendorRepository vendorRepository, ModelMapper modelMapper, UserService userService) {
        this.vendorRepository = vendorRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<Vendor> findAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public void addVendor(VendorDTO vendorDTO, HttpSession session) {
        Vendor vendor = modelMapper.map(vendorDTO, Vendor.class);
        //get and set Author
        vendor.setAuthor(userService.getAuthorFromSession(session));
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
    public void editVendor(String name, String country, String city, String address, String vatNumber, String email, Long id, HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        vendorRepository.editVendor(name,country,city,address,vatNumber,email, id,editAuthorId, dateEdit);
    }
}

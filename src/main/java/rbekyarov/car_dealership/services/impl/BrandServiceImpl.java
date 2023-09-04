package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.repository.BrandRepository;
import rbekyarov.car_dealership.services.BrandService;
import rbekyarov.car_dealership.services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper, UserService userService) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public List<Brand> findAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public void addBrand(BrandDTO brandDTO, HttpSession session) {
        Brand brand = modelMapper.map(brandDTO, Brand.class);
        //get and set Author
        brand.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        brand.setDateCreate(LocalDate.now());
        brandRepository.save(brand);
    }

    @Override
    public void removeBrandById(Long id) {
     brandRepository.deleteById(id);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public void editBrand(String name, Long id, HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        brandRepository.editBrand(name, id,editAuthorId, dateEdit);

    }
}

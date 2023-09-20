package rbekyarov.car_dealership.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.repository.BrandRepository;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.services.BrandService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static rbekyarov.car_dealership.services.CommonService.getUserEntity;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;


    private final UserRepository userRepository;


    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;

    }


    @Override
    public List<Brand> findAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public void addBrand(BrandDTO brandDTO) {
        Brand brand = modelMapper.map(brandDTO, Brand.class);

        UserEntity user = getUserEntity();
        brand.setAuthor(user);

        brand.setAuthor(userRepository.getUsersById(1L));
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
    public void editBrand(String name, Long id) {

      UserEntity user = getUserEntity();
      Long editUserId = user.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        brandRepository.editBrand(name, id,editUserId, dateEdit);

    }
}

package rbekyarov.car_dealership.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.ModelDTO;
import rbekyarov.car_dealership.models.entity.Model;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.repository.BrandRepository;
import rbekyarov.car_dealership.repository.ModelRepository;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.services.ModelService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static rbekyarov.car_dealership.services.CommonService.getUserEntity;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper, BrandRepository brandRepository, UserRepository userRepository) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Model> findAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public void addModel(ModelDTO modelDTO) {
        Model model = modelMapper.map(modelDTO, Model.class);
        model.setBrand(brandRepository.findById(modelDTO.getBrandId()).orElseThrow());
        UserEntity user = getUserEntity();
        model.setAuthor(user);

        // set dateCreated
        model.setDateCreate(LocalDate.now());
        modelRepository.save(model);
    }

    @Override
    public void removeModelById(Long id) {
        modelRepository.deleteById(id);
    }

    @Override
    public Optional<Model> findById(Long id) {
        return modelRepository.findById(id);
    }

    @Override
    public void editModel(String name, Long brandId, Long id) {
        UserEntity user = getUserEntity();
        Long editUserId = user.getId();


        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        modelRepository.editModel(name,brandId, id,editUserId, dateEdit);
    }
}

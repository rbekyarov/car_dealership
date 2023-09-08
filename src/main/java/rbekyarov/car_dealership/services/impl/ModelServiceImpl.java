package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.ModelDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Model;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.repository.BrandRepository;
import rbekyarov.car_dealership.repository.ModelRepository;
import rbekyarov.car_dealership.services.ModelService;
import rbekyarov.car_dealership.services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final BrandRepository brandRepository;

    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper, UserService userService,
                            BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Model> findAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public void addModel(ModelDTO modelDTO, HttpSession session) {
        Model model = modelMapper.map(modelDTO, Model.class);
        model.setBrand(brandRepository.findById(modelDTO.getBrandId()).orElseThrow());
        model.setAuthor(userService.getAuthorFromSession(session));
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
    public void editModel(String name, Long brandId, Long id, HttpSession session) {
        User editUser = userService.getAuthorFromSession(session);
        Long editUserId = editUser.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        modelRepository.editModel(name,brandId, id,editUserId, dateEdit);
    }
}

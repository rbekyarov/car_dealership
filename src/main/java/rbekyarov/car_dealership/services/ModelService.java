package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.dto.ModelDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Model;

import java.util.List;
import java.util.Optional;

public interface ModelService {

    List<Model> findAllModels();

    void addModel(ModelDTO modelDTO);

    void removeModelById(Long id);

    Optional<Model> findById(Long id);

    void editModel(String name,Long brandId, Long id);
}

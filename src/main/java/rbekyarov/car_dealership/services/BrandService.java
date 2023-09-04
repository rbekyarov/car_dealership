package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<Brand> findAllBrands();

    void addBrand(BrandDTO brandDTO, HttpSession session);

    void removeBrandById(Long id);

    Optional<Brand> findById(Long id);

    void editBrand(String name, Long id, HttpSession session);


}

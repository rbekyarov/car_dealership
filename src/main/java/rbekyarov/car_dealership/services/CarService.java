package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.dto.CarDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAllBrands();

    void addCar(CarDTO carDTO, HttpSession session);

    void removeCarById(Long id);

    Optional<Car> findById(Long id);

    void editCar(CarDTO carDTO, Long id, HttpSession session);
}

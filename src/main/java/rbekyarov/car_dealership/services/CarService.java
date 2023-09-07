package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.CarDTO;
import rbekyarov.car_dealership.models.entity.Car;
import rbekyarov.car_dealership.models.entity.Picture;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CarService {
    List<Car> findAllCars();

    void addCar(CarDTO carDTO, Set<Picture>pictures, HttpSession session);

    void removeCarById(Long id);

    Optional<Car> findById(Long id);

    void editCar(CarDTO carDTO,Set<Picture>pictures, Long id, HttpSession session);

    BigDecimal calculatePriceOnCars(Set<Long> carsIds);

    Set<Car> addCarInOfferAndSale(Set<Long> carIds);

    void updatePricesAfterAddCost(BigDecimal priceCosts, BigDecimal priceSale, BigDecimal priceSaleMin, Long id);
}

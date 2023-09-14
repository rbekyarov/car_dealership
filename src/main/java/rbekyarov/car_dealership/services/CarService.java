package rbekyarov.car_dealership.services;

import rbekyarov.car_dealership.models.dto.CarDTO;
import rbekyarov.car_dealership.models.entity.Car;
import rbekyarov.car_dealership.models.entity.Picture;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CarService {
    List<Car> findAllCars();

    void addCar(CarDTO carDTO, Set<Picture>pictures);

    void removeCarById(Long id);

    Optional<Car> findById(Long id);

    void editCar(CarDTO carDTO,Set<Picture>pictures, Long id);

    BigDecimal calculatePriceOnCars(Set<Long> carsIds);

    Set<Car> addCarInOfferAndSale(Set<Long> carIds);

    void updatePricesAfterAddCost(BigDecimal priceCosts, BigDecimal priceSale, BigDecimal priceSaleMin, Long id);

    void updateCarOfferIdFields(Set<Car> cars, Long nextOfferId);

    List<Car> findAllCarsOnThisOfferId(Long id);

    void deleteCarIdAndOfferId(Long carId, Long offerId);
    void deleteCarIdAndSaleId(Long carId, Long saleId);

    void updateProfitForCar(BigDecimal profit,Long id);

    void updateCommissionForCar(BigDecimal multiply, Long id);

    void updateStatusAvailableSold(Long id);

    void updateDateSold(LocalDate dateSold, Long id);

    void updateStatusAvailableAvailable(Long id);


}

package rbekyarov.car_dealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Car;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    @Query("select c.priceSale from Car as c where c.id=:id")
    BigDecimal calculatePriceOnCars(Long id);
}

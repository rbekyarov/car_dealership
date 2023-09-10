package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.dto.CostDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Cost;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CostService {
    List<Cost> findAllCosts();

    void addCost(CostDTO costDTO, HttpSession session);

    void removeCostById(Long id);

    Optional<Cost> findById(Long id);

    void editCost(Long vendorId,Long carId,String description,String invoiceNo,BigDecimal amount, LocalDate dateCost, Long id, HttpSession session);

}

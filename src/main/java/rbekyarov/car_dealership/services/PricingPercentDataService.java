package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.PricingPercentDataDTO;

import rbekyarov.car_dealership.models.entity.PricingPercentData;
import rbekyarov.car_dealership.models.entity.enums.ActivePricingPercentData;

import java.util.List;
import java.util.Optional;

public interface PricingPercentDataService {
    List<PricingPercentData> findAllPricingPercentDates();
    void addPricingPercentData(PricingPercentDataDTO pricingPercentDataDTO, HttpSession session);

    void removePricingPercentDataById(Long id);

    Optional<PricingPercentData> findById(Long id);

    void editPricingPercentData(int percentSaleCar, int percentSaleCarMin, int percentCommission, ActivePricingPercentData activePricingPercentData, Long id, HttpSession session);
}

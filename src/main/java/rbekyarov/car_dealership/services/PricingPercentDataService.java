package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.PricingPercentDataDTO;

import rbekyarov.car_dealership.models.entity.PricingPercentData;
import rbekyarov.car_dealership.models.entity.enums.ActivePricingPercentData;

import java.util.List;
import java.util.Optional;

public interface PricingPercentDataService {
    List<PricingPercentData> findAllPricingPercentDates();
    void addPricingPercentData(PricingPercentDataDTO pricingPercentDataDTO);

    void removePricingPercentDataById(Long id);

    Optional<PricingPercentData> findById(Long id);
    Optional<PricingPercentData> findActivePricingPercentData();

    void editPricingPercentData(int percentSaleCar, int percentSaleCarMin, int percentCommission, ActivePricingPercentData activePricingPercentData,int percentVAT, Long id);
}

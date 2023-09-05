package rbekyarov.car_dealership.models.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import rbekyarov.car_dealership.models.entity.enums.ActivePricingPercentData;

public class PricingPercentDataDTO {
    private int percentSaleCar;
    private int percentSaleCarMin;
    private int percentCommission;
    private ActivePricingPercentData activePricingPercentData;
    public PricingPercentDataDTO() {
    }

    @Min(0)
    @Max(300)
    public int getPercentSaleCar() {
        return percentSaleCar;
    }

    public void setPercentSaleCar(int percentSaleCar) {
        this.percentSaleCar = percentSaleCar;
    }
    @Min(0)
    @Max(300)
    public int getPercentSaleCarMin() {
        return percentSaleCarMin;
    }

    public void setPercentSaleCarMin(int percentSaleCarMin) {
        this.percentSaleCarMin = percentSaleCarMin;
    }
    @Min(0)
    @Max(50)
    public int getPercentCommission() {
        return percentCommission;
    }

    public void setPercentCommission(int percentCommission) {
        this.percentCommission = percentCommission;
    }

    public ActivePricingPercentData getActivePricingPercentData() {
        return activePricingPercentData;
    }

    public void setActivePricingPercentData(ActivePricingPercentData activePricingPercentData) {
        this.activePricingPercentData = activePricingPercentData;
    }
}

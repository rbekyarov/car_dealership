package rbekyarov.car_dealership.models.dto;


import rbekyarov.car_dealership.models.entity.enums.IsMainCurrency;

public class CurrencyDTO  {
    private String code;
    private String name;
    private double exchangeRate;
    private IsMainCurrency isMainCurrency;
    public CurrencyDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public IsMainCurrency getIsMainCurrency() {
        return isMainCurrency;
    }

    public void setIsMainCurrency(IsMainCurrency isMainCurrency) {
        this.isMainCurrency = isMainCurrency;
    }
}

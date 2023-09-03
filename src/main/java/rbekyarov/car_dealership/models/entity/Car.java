package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import rbekyarov.car_dealership.models.entity.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
@Entity
@Table(name = "cars")
public class Car {
    private String name;
    private Set<Picture> pictures;
    private String vinNumber;
    private Model model;
    private Transmision transmision;
    private FuelType fuelType;
    private BigDecimal power;
    private BigDecimal cubature;
    private Color color;
    private DoorCount doorCount;
    private Vendor vendorPurchase;
    private Set<Costs> costs;
    private Eurostandard eurostandard;
    private Category category;

    //Options
    private AutoStartStop autoStartStop;
    private Metallic metallic;
    private ServiceBook serviceBook;
    private Alarm alarm;
    private LeatherSalon leatherSalon;
    private HalogenHeadlights halogenHeadlights;
    private Parktronik parktronik;
    private Airbags airbags;
    private ElMirrors elMirrors;
    private ElWindows elWindows;
    private Climatic climatic;
    private Navigation navigation;

    //statusAvailable
    private StatusAvailable statusAvailable;

    //Date:
    private LocalDate regDate;
    private LocalDate datePurchase;
    private LocalDate dateIncome;
    private LocalDate dateSale;
    private LocalDate dateCreate;

    //Prices:
    private BigDecimal pricePurchase;
    private BigDecimal priceCosts;
    private BigDecimal priceSaleMin;
    private BigDecimal priceSale;
    private BigDecimal priceProfit;
    private BigDecimal priceCommission;

    //author
    private User author;

    public Car() {
    }

}

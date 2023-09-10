package rbekyarov.car_dealership.models.dto;

import rbekyarov.car_dealership.models.entity.Cost;
import rbekyarov.car_dealership.models.entity.Currency;
import rbekyarov.car_dealership.models.entity.Model;
import rbekyarov.car_dealership.models.entity.Picture;
import rbekyarov.car_dealership.models.entity.Vendor;
import rbekyarov.car_dealership.models.entity.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class CarDTO {
    private Long id;
    private Set<Picture> pictures;
    private String vinNumber;
    private Long modelId;
    private Transmision transmision;
    private FuelType fuelType;
    private BigDecimal horsepower;
    private BigDecimal cubature;
    private ConditionCar conditionCar;
    private Color color;
    private DoorCount doorCount;
    private Long vendorId;
    private Set<Cost> costs;
    private Eurostandard eurostandard;
    private Category category;

    private String comments;

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
    private LocalDate dateIncome;
    private LocalDate datePurchase;
    private Long currencyId;
    private BigDecimal pricePurchase;

    public CarDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Transmision getTransmision() {
        return transmision;
    }

    public void setTransmision(Transmision transmision) {
        this.transmision = transmision;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(BigDecimal horsepower) {
        this.horsepower = horsepower;
    }

    public BigDecimal getCubature() {
        return cubature;
    }

    public void setCubature(BigDecimal cubature) {
        this.cubature = cubature;
    }

    public ConditionCar getConditionCar() {
        return conditionCar;
    }

    public void setConditionCar(ConditionCar conditionCar) {
        this.conditionCar = conditionCar;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public DoorCount getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(DoorCount doorCount) {
        this.doorCount = doorCount;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Set<Cost> getCosts() {
        return costs;
    }

    public void setCosts(Set<Cost> costs) {
        this.costs = costs;
    }

    public Eurostandard getEurostandard() {
        return eurostandard;
    }

    public void setEurostandard(Eurostandard eurostandard) {
        this.eurostandard = eurostandard;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public AutoStartStop getAutoStartStop() {
        return autoStartStop;
    }

    public void setAutoStartStop(AutoStartStop autoStartStop) {
        this.autoStartStop = autoStartStop;
    }

    public Metallic getMetallic() {
        return metallic;
    }

    public void setMetallic(Metallic metallic) {
        this.metallic = metallic;
    }

    public ServiceBook getServiceBook() {
        return serviceBook;
    }

    public void setServiceBook(ServiceBook serviceBook) {
        this.serviceBook = serviceBook;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public LeatherSalon getLeatherSalon() {
        return leatherSalon;
    }

    public void setLeatherSalon(LeatherSalon leatherSalon) {
        this.leatherSalon = leatherSalon;
    }

    public HalogenHeadlights getHalogenHeadlights() {
        return halogenHeadlights;
    }

    public void setHalogenHeadlights(HalogenHeadlights halogenHeadlights) {
        this.halogenHeadlights = halogenHeadlights;
    }

    public Parktronik getParktronik() {
        return parktronik;
    }

    public void setParktronik(Parktronik parktronik) {
        this.parktronik = parktronik;
    }

    public Airbags getAirbags() {
        return airbags;
    }

    public void setAirbags(Airbags airbags) {
        this.airbags = airbags;
    }

    public ElMirrors getElMirrors() {
        return elMirrors;
    }

    public void setElMirrors(ElMirrors elMirrors) {
        this.elMirrors = elMirrors;
    }

    public ElWindows getElWindows() {
        return elWindows;
    }

    public void setElWindows(ElWindows elWindows) {
        this.elWindows = elWindows;
    }

    public Climatic getClimatic() {
        return climatic;
    }

    public void setClimatic(Climatic climatic) {
        this.climatic = climatic;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    public StatusAvailable getStatusAvailable() {
        return statusAvailable;
    }

    public void setStatusAvailable(StatusAvailable statusAvailable) {
        this.statusAvailable = statusAvailable;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public LocalDate getDateIncome() {
        return dateIncome;
    }

    public void setDateIncome(LocalDate dateIncome) {
        this.dateIncome = dateIncome;
    }

    public LocalDate getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(LocalDate datePurchase) {
        this.datePurchase = datePurchase;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getPricePurchase() {
        return pricePurchase;
    }

    public void setPricePurchase(BigDecimal pricePurchase) {
        this.pricePurchase = pricePurchase;
    }
}

package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    private String name;
    private Set<Picture> pictures;
    private String vinNumber;
    private Model model;
    private Transmision transmision;
    private FuelType fuelType;
    private BigDecimal power;
    private BigDecimal cubature;
    private ConditionCar conditionCar;
    private Color color;
    private DoorCount doorCount;
    private Vendor vendorPurchase;
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

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "picture_id", referencedColumnName = "id")
    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @Column
    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Enumerated(EnumType.STRING)
    public Transmision getTransmision() {
        return transmision;
    }

    public void setTransmision(Transmision transmision) {
        this.transmision = transmision;
    }

    @Enumerated(EnumType.STRING)
    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @Column
    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    @Column
    public BigDecimal getCubature() {
        return cubature;
    }

    public void setCubature(BigDecimal cubature) {
        this.cubature = cubature;
    }
    @Enumerated(EnumType.STRING)
    public ConditionCar getConditionCar() {
        return conditionCar;
    }

    public void setConditionCar(ConditionCar conditionCar) {
        this.conditionCar = conditionCar;
    }




    @Enumerated(EnumType.STRING)
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Enumerated(EnumType.STRING)
    public DoorCount getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(DoorCount doorCount) {
        this.doorCount = doorCount;
    }

    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    public Vendor getVendorPurchase() {
        return vendorPurchase;
    }

    public void setVendorPurchase(Vendor vendorPurchase) {
        this.vendorPurchase = vendorPurchase;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "cost_id", referencedColumnName = "id")
    public Set<Cost> getCosts() {
        return costs;
    }

    public void setCosts(Set<Cost> costs) {
        this.costs = costs;
    }

    @Enumerated(EnumType.STRING)
    public Eurostandard getEurostandard() {
        return eurostandard;
    }

    public void setEurostandard(Eurostandard eurostandard) {
        this.eurostandard = eurostandard;
    }

    @Enumerated(EnumType.STRING)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Enumerated(EnumType.STRING)
    public AutoStartStop getAutoStartStop() {
        return autoStartStop;
    }

    public void setAutoStartStop(AutoStartStop autoStartStop) {
        this.autoStartStop = autoStartStop;
    }

    @Enumerated(EnumType.STRING)
    public Metallic getMetallic() {
        return metallic;
    }

    public void setMetallic(Metallic metallic) {
        this.metallic = metallic;
    }

    @Enumerated(EnumType.STRING)
    public ServiceBook getServiceBook() {
        return serviceBook;
    }

    public void setServiceBook(ServiceBook serviceBook) {
        this.serviceBook = serviceBook;
    }

    @Enumerated(EnumType.STRING)
    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    @Enumerated(EnumType.STRING)
    public LeatherSalon getLeatherSalon() {
        return leatherSalon;
    }

    public void setLeatherSalon(LeatherSalon leatherSalon) {
        this.leatherSalon = leatherSalon;
    }

    @Enumerated(EnumType.STRING)
    public HalogenHeadlights getHalogenHeadlights() {
        return halogenHeadlights;
    }

    public void setHalogenHeadlights(HalogenHeadlights halogenHeadlights) {
        this.halogenHeadlights = halogenHeadlights;
    }

    @Enumerated(EnumType.STRING)
    public Parktronik getParktronik() {
        return parktronik;
    }

    public void setParktronik(Parktronik parktronik) {
        this.parktronik = parktronik;
    }

    @Enumerated(EnumType.STRING)
    public Airbags getAirbags() {
        return airbags;
    }

    public void setAirbags(Airbags airbags) {
        this.airbags = airbags;
    }

    @Enumerated(EnumType.STRING)
    public ElMirrors getElMirrors() {
        return elMirrors;
    }

    public void setElMirrors(ElMirrors elMirrors) {
        this.elMirrors = elMirrors;
    }

    @Enumerated(EnumType.STRING)
    public ElWindows getElWindows() {
        return elWindows;
    }

    public void setElWindows(ElWindows elWindows) {
        this.elWindows = elWindows;
    }

    @Enumerated(EnumType.STRING)
    public Climatic getClimatic() {
        return climatic;
    }

    public void setClimatic(Climatic climatic) {
        this.climatic = climatic;
    }

    @Enumerated(EnumType.STRING)
    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    @Enumerated(EnumType.STRING)
    public StatusAvailable getStatusAvailable() {
        return statusAvailable;
    }

    public void setStatusAvailable(StatusAvailable statusAvailable) {
        this.statusAvailable = statusAvailable;
    }

    @Column
    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Column
    public LocalDate getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(LocalDate datePurchase) {
        this.datePurchase = datePurchase;
    }

    @Column
    public LocalDate getDateIncome() {
        return dateIncome;
    }

    public void setDateIncome(LocalDate dateIncome) {
        this.dateIncome = dateIncome;
    }

    @Column
    public LocalDate getDateSale() {
        return dateSale;
    }

    public void setDateSale(LocalDate dateSale) {
        this.dateSale = dateSale;
    }

    @Column
    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Column
    public BigDecimal getPricePurchase() {
        return pricePurchase;
    }

    public void setPricePurchase(BigDecimal pricePurchase) {
        this.pricePurchase = pricePurchase;
    }

    @Column
    public BigDecimal getPriceCosts() {
        return priceCosts;
    }

    public void setPriceCosts(BigDecimal priceCosts) {
        this.priceCosts = priceCosts;
    }

    @Column
    public BigDecimal getPriceSaleMin() {
        return priceSaleMin;
    }

    public void setPriceSaleMin(BigDecimal priceSaleMin) {
        this.priceSaleMin = priceSaleMin;
    }

    @Column
    public BigDecimal getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(BigDecimal priceSale) {
        this.priceSale = priceSale;
    }

    @Column
    public BigDecimal getPriceProfit() {
        return priceProfit;
    }

    public void setPriceProfit(BigDecimal priceProfit) {
        this.priceProfit = priceProfit;
    }

    @Column
    public BigDecimal getPriceCommission() {
        return priceCommission;
    }

    public void setPriceCommission(BigDecimal priceCommission) {
        this.priceCommission = priceCommission;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}

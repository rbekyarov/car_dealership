package rbekyarov.car_dealership.models.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    @Column
    private String name;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)

    private Set<Picture> pictures;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)

    private Set<Offer> offers;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Sale> sales;
    @Column
    private String vinNumber;
    @ManyToOne
    private Model model;
    @Enumerated(EnumType.STRING)
    private Transmision transmision;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Column
    private BigDecimal horsepower;
    @Column
    private BigDecimal cubature;
    @Enumerated(EnumType.STRING)
    private ConditionCar conditionCar;
    @Enumerated(EnumType.STRING)
    private Color color;
    @Column
    private DoorCount doorCount;
    @ManyToOne
    private Vendor vendorPurchase;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Cost> costs;
    @Enumerated(EnumType.STRING)
    private Eurostandard eurostandard;
    @Enumerated(EnumType.STRING)
    private Category category;

    private String comments;

    //Options
    @Enumerated(EnumType.STRING)
    private AutoStartStop autoStartStop;
    @Enumerated(EnumType.STRING)
    private Metallic metallic;
    @Enumerated(EnumType.STRING)
    private ServiceBook serviceBook;
    @Enumerated(EnumType.STRING)
    private Alarm alarm;
    @Enumerated(EnumType.STRING)
    private LeatherSalon leatherSalon;
    @Enumerated(EnumType.STRING)
    private HalogenHeadlights halogenHeadlights;
    @Enumerated(EnumType.STRING)
    private Parktronik parktronik;
    @Enumerated(EnumType.STRING)
    private Airbags airbags;
    @Enumerated(EnumType.STRING)
    private ElMirrors elMirrors;
    @Enumerated(EnumType.STRING)
    private ElWindows elWindows;
    @Enumerated(EnumType.STRING)
    private Climatic climatic;
    @Enumerated(EnumType.STRING)
    private Navigation navigation;

    //statusAvailable
    @Enumerated(EnumType.STRING)
    private StatusAvailable statusAvailable;

    //Date:
    @Column
    private LocalDate regDate;
    @Column
    private LocalDate datePurchase;
    @Column
    private LocalDate dateIncome;
    @Column
    private LocalDate dateSold;
    @Column
    private LocalDate dateCreate;

    //Prices:
    @ManyToOne
    private Currency currency;
    @Column
    private BigDecimal pricePurchase;
    @Column
    private BigDecimal priceCosts;
    @Column
    private BigDecimal priceSaleMin;
    @Column
    private BigDecimal priceSale;
    @Column
    private BigDecimal priceProfit;
    @Column
    private BigDecimal priceCommission;

    //author
    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;

    public Car() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }


    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }



    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
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

    public Vendor getVendorPurchase() {
        return vendorPurchase;
    }

    public void setVendorPurchase(Vendor vendorPurchase) {
        this.vendorPurchase = vendorPurchase;
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


    public LocalDate getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(LocalDate datePurchase) {
        this.datePurchase = datePurchase;
    }


    public LocalDate getDateIncome() {
        return dateIncome;
    }

    public void setDateIncome(LocalDate dateIncome) {
        this.dateIncome = dateIncome;
    }

    public LocalDate getDateSold() {
        return dateSold;
    }

    public void setDateSold(LocalDate dateSold) {
        this.dateSold = dateSold;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }


    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }


    public BigDecimal getPricePurchase() {
        return pricePurchase;
    }

    public void setPricePurchase(BigDecimal pricePurchase) {
        this.pricePurchase = pricePurchase;
    }


    public BigDecimal getPriceCosts() {
        return priceCosts;
    }

    public void setPriceCosts(BigDecimal priceCosts) {
        this.priceCosts = priceCosts;
    }


    public BigDecimal getPriceSaleMin() {
        return priceSaleMin;
    }

    public void setPriceSaleMin(BigDecimal priceSaleMin) {
        this.priceSaleMin = priceSaleMin;
    }


    public BigDecimal getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(BigDecimal priceSale) {
        this.priceSale = priceSale;
    }


    public BigDecimal getPriceProfit() {
        return priceProfit;
    }

    public void setPriceProfit(BigDecimal priceProfit) {
        this.priceProfit = priceProfit;
    }


    public BigDecimal getPriceCommission() {
        return priceCommission;
    }

    public void setPriceCommission(BigDecimal priceCommission) {
        this.priceCommission = priceCommission;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public Car setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public UserEntity getEditUser() {
        return editUser;
    }

    public Car setEditUser(UserEntity editUser) {
        this.editUser = editUser;
        return this;
    }

    public LocalDate getDateEdite() {
        return dateEdite;
    }

    public void setDateEdite(LocalDate dateEdite) {
        this.dateEdite = dateEdite;
    }

}

package rbekyarov.car_dealership.models.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.StatusOffer;
import rbekyarov.car_dealership.models.entity.enums.StatusSalesInvoiced;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
    @JoinColumn( name = "sale_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "pictures", "offers","sales", "vinNumber","model","transmision","fuelType","horsepower","cubature","conditionCar", "color","doorCount","vendorPurchase","costs","eurostandard","category","comments", "autoStartStop","metallic","serviceBook","alarm","leatherSalon","halogenHeadlights","parktronik","airbags","elMirrors","elWindows","climatic", "navigation","statusAvailable","regDate","datePurchase","dateIncome","dateSold","dateCreate","currency","pricePurchase", "priceCosts","priceSaleMin","priceSale","priceProfit","priceCommission","author","editUser","dateEdite"})
    @ManyToMany(cascade = CascadeType.MERGE,  fetch = FetchType.EAGER)
    @JoinTable(
            name = "sale_car",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private Set<Car> cars;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"position", "offers", "sales","salary", "monthlyProfit", "totalProfit","author","dateCreate","editUser","dateEdite"})
    private Seller seller;
    @ManyToOne
    @JsonIgnoreProperties(value = {"vatOrId", "email", "email","phone", "city", "address","clientType","offers","sales","author","dateCreate","editUser","dateEdite"})

    private Client client;
    @ManyToOne
    @JsonIgnoreProperties(value = {"name","logoName","country","city","address","vatNumber","email","managerName","bankAccounts","author","dateCreate","editUser","dateEdite"})

    private Company company;
    @Enumerated(EnumType.STRING)
    private StatusSalesInvoiced statusSalesInvoiced;
    @ManyToOne
    @JsonIgnoreProperties(value = {"exchangeRate","isMainCurrency","author","dateCreate","editUser","dateEdite"})

    private Currency currency;
    @Column
    private BigDecimal price;
    @Column
    private BigDecimal discount;
    @Column
    private BigDecimal totalPrice;
    @Column
    private LocalDate dateCreate;
    @ManyToOne
    @JsonIgnoreProperties(value = {"firstName", "lastName", "email","roles", "position"})
    private UserEntity author;
    @ManyToOne
    @JsonIgnoreProperties(value = {"firstName", "lastName", "email","roles", "position"})
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;
    public Sale() {
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }


    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public StatusSalesInvoiced getStatusSalesInvoiced() {
        return statusSalesInvoiced;
    }

    public void setStatusSalesInvoiced(StatusSalesInvoiced statusSalesInvoiced) {
        this.statusSalesInvoiced = statusSalesInvoiced;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public Sale setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public UserEntity getEditUser() {
        return editUser;
    }

    public Sale setEditUser(UserEntity editUser) {
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

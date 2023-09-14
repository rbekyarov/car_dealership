package rbekyarov.car_dealership.models.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {
    @Column
    private String name;
    @ManyToOne
    @JsonIgnoreProperties(value = {"firstName", "lastName", "email","roles", "position"})
    private UserEntity author;
    @Column
    private LocalDate dateCreate;
    @ManyToOne
    @JsonIgnoreProperties(value = {"firstName", "lastName", "email","roles", "position"})
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;
    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonIgnoreProperties(value = { "pictures", "offers","sales", "vinNumber","model","transmision","fuelType","horsepower","cubature","conditionCar", "color","doorCount","vendorPurchase","costs","eurostandard","category","comments", "autoStartStop","metallic","serviceBook","alarm","leatherSalon","halogenHeadlights","parktronik","airbags","elMirrors","elWindows","climatic", "navigation","statusAvailable","regDate","datePurchase","dateIncome","dateSold","dateCreate","currency","pricePurchase", "priceCosts","priceSaleMin","priceSale","priceProfit","priceCommission","author","editUser","dateEdite"})
    private Car car;

    public Picture() {
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDate getDateEdite() {
        return dateEdite;
    }

    public void setDateEdite(LocalDate dateEdite) {
        this.dateEdite = dateEdite;
    }
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public Picture setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
    public UserEntity getEditUser() {
        return editUser;
    }

    public Picture setEditUser(UserEntity editUser) {
        this.editUser = editUser;
        return this;
    }
}

package rbekyarov.car_dealership.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.IsMainCurrency;

import java.time.LocalDate;

@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity {
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private double exchangeRate;
    @Enumerated(EnumType.STRING)
    private IsMainCurrency isMainCurrency;
    @ManyToOne
    private UserEntity author;
    @Column
    private LocalDate dateCreate;
    @ManyToOne
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;

    public Currency() {
    }

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
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

    public IsMainCurrency getIsMainCurrency() {
        return isMainCurrency;
    }

    public void setIsMainCurrency(IsMainCurrency isMainCurrency) {
        this.isMainCurrency = isMainCurrency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
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

    public UserEntity getAuthor() {
        return author;
    }

    public Currency setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
    public UserEntity getEditUser() {
        return editUser;
    }

    public Currency setEditUser(UserEntity editUser) {
        this.editUser = editUser;
        return this;
    }
}

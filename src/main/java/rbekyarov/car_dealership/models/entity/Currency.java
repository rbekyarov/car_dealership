package rbekyarov.car_dealership.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.IsMainCurrency;

import java.time.LocalDate;

@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity {
    private String code;
    private String name;
    private double exchangeRate;
    private IsMainCurrency isMainCurrency;
    private User author;
    private LocalDate dateCreate;
    private User editUser;
    private LocalDate dateEdite;

    public Currency() {
    }

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Column(nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Enumerated(EnumType.STRING)
    public IsMainCurrency getIsMainCurrency() {
        return isMainCurrency;
    }

    public void setIsMainCurrency(IsMainCurrency isMainCurrency) {
        this.isMainCurrency = isMainCurrency;
    }
    @Column
    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    @JsonIgnoreProperties({"email", "role","position","id"})
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }
    @JsonIgnoreProperties({"email", "role","position","id"})
    @ManyToOne
    @JoinColumn(name = "edit_user_id", referencedColumnName = "id")
    public User getEditUser() {
        return editUser;
    }

    public void setEditUser(User editUser) {
        this.editUser = editUser;
    }

    public LocalDate getDateEdite() {
        return dateEdite;
    }

    public void setDateEdite(LocalDate dateEdite) {
        this.dateEdite = dateEdite;
    }
}

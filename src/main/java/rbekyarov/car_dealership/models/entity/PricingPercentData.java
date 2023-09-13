package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.ActivePricingPercentData;

import java.time.LocalDate;

@Entity
@Table(name = "percents")
public class PricingPercentData extends BaseEntity {
    @Column
    private int percentSaleCar;
    @Column
    private int percentSaleCarMin;
    @Column
    private int percentCommission;
    @Enumerated(EnumType.STRING)
    private ActivePricingPercentData activePricingPercentData;
    @Column
    private int percentVAT;
    @ManyToOne
    private UserEntity author;
    @Column
    private LocalDate dateCreate;
    @ManyToOne
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;

    public PricingPercentData(int percentSaleCar, int percentSaleCarMin, int percentCommission, ActivePricingPercentData activePricingPercentData, int percentVAT) {
        this.percentSaleCar = percentSaleCar;
        this.percentSaleCarMin = percentSaleCarMin;
        this.percentCommission = percentCommission;
        this.activePricingPercentData = activePricingPercentData;
        this.percentVAT = percentVAT;
    }

    public PricingPercentData() {
    }

    public int getPercentSaleCar() {
        return percentSaleCar;
    }

    public void setPercentSaleCar(int percentSaleCar) {
        this.percentSaleCar = percentSaleCar;
    }

    public int getPercentSaleCarMin() {
        return percentSaleCarMin;
    }

    public void setPercentSaleCarMin(int percentSaleCarMin) {
        this.percentSaleCarMin = percentSaleCarMin;
    }

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

    public int getPercentVAT() {
        return percentVAT;
    }

    public void setPercentVAT(int percentVAT) {
        this.percentVAT = percentVAT;
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

    public PricingPercentData setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public UserEntity getEditUser() {
        return editUser;
    }

    public PricingPercentData setEditUser(UserEntity editUser) {
        this.editUser = editUser;
        return this;
    }
}

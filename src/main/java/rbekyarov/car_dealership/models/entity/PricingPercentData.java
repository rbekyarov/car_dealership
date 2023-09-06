package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.ActivePricingPercentData;

import java.time.LocalDate;

@Entity
@Table(name = "percents")
public class PricingPercentData extends BaseEntity {
    private int percentSaleCar;
    private int percentSaleCarMin;
    private int percentCommission;
    private ActivePricingPercentData activePricingPercentData;
    private int percentVAT;
    private User author;

    private LocalDate dateCreate;

    public PricingPercentData(int percentSaleCar, int percentSaleCarMin, int percentCommission, ActivePricingPercentData activePricingPercentData, int percentVAT) {
        this.percentSaleCar = percentSaleCar;
        this.percentSaleCarMin = percentSaleCarMin;
        this.percentCommission = percentCommission;
        this.activePricingPercentData = activePricingPercentData;
        this.percentVAT = percentVAT;
    }

    public PricingPercentData() {
    }
@Column
    public int getPercentSaleCar() {
        return percentSaleCar;
    }

    public void setPercentSaleCar(int percentSaleCar) {
        this.percentSaleCar = percentSaleCar;
    }
    @Column
    public int getPercentSaleCarMin() {
        return percentSaleCarMin;
    }

    public void setPercentSaleCarMin(int percentSaleCarMin) {
        this.percentSaleCarMin = percentSaleCarMin;
    }
    @Column
    public int getPercentCommission() {
        return percentCommission;
    }

    public void setPercentCommission(int percentCommission) {
        this.percentCommission = percentCommission;
    }
    @Enumerated(EnumType.STRING)
    public ActivePricingPercentData getActivePricingPercentData() {
        return activePricingPercentData;
    }

    public void setActivePricingPercentData(ActivePricingPercentData activePricingPercentData) {
        this.activePricingPercentData = activePricingPercentData;
    }
    @Column
    public int getPercentVAT() {
        return percentVAT;
    }

    public void setPercentVAT(int percentVAT) {
        this.percentVAT = percentVAT;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
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

}

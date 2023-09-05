package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "percents")
public class PricingPercentData extends BaseEntity {
    private int percentSaleCar;
    private int percentSaleCarMin;
    private int percentCommission;
    private User author;

    private LocalDate dateCreate;

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

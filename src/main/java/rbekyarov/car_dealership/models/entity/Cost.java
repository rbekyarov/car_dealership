package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "costs")
public class Cost extends BaseEntity {
    @ManyToOne
    private Vendor vendor;
    @ManyToOne
    private Car car;
    @Column
    private String description;
    @Column
    private String invoiceNo;
    @ManyToOne
    private Currency currency;
    @Column
    private BigDecimal amount;
    @Column
    private LocalDate dateCost;
    @ManyToOne
    private UserEntity author;
    @Column
    private LocalDate dateCreate;
    @ManyToOne
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;
    public Cost() {
    }

    public Cost(Vendor vendor, String description, String invoiceNo, BigDecimal amount, LocalDate dateCost, UserEntity author, LocalDate dateCreate) {
        this.vendor = vendor;
        this.description = description;
        this.invoiceNo = invoiceNo;
        this.amount = amount;
        this.dateCost = dateCost;
        this.author = author;
        this.dateCreate = dateCreate;
    }


    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }


    public LocalDate getDateCost() {
        return dateCost;
    }

    public void setDateCost(LocalDate dateCost) {
        this.dateCost = dateCost;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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

    public Cost setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public UserEntity getEditUser() {
        return editUser;
    }

    public Cost setEditUser(UserEntity editUser) {
        this.editUser = editUser;
        return this;
    }
}


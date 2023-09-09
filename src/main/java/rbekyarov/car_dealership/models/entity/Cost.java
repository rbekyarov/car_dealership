package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "costs")
public class Cost extends BaseEntity {
    private Vendor vendor;
    private Car car;
    private String description;
    private String invoiceNo;
    private Currency currency;

    private BigDecimal amount;
    private LocalDate dateCost;
    private User author;

    private LocalDate dateCreate;
    private User editUser;
    private LocalDate dateEdite;
    public Cost() {
    }

    public Cost(Vendor vendor, String description, String invoiceNo, BigDecimal amount, LocalDate dateCost, User author, LocalDate dateCreate) {
        this.vendor = vendor;
        this.description = description;
        this.invoiceNo = invoiceNo;
        this.amount = amount;
        this.dateCost = dateCost;
        this.author = author;
        this.dateCreate = dateCreate;
    }

    @ManyToOne
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    @ManyToOne
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Column
    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Column
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
    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
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


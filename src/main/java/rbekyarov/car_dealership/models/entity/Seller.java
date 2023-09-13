package rbekyarov.car_dealership.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import rbekyarov.car_dealership.models.entity.enums.Position;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity {
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Position position;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "seller_id", referencedColumnName = "id")
    private Set<Offer> offers;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn( name = "seller_id", referencedColumnName = "id")
    private Set<Sale> sales;
    @Column
    private BigDecimal salary;
    @Column
    private BigDecimal monthlyProfit;
    @Column
    private BigDecimal totalProfit;
    @ManyToOne
    private UserEntity author;
    @Column
    private LocalDate dateCreate;
    @ManyToOne
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;
    public Seller() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getMonthlyProfit() {
        return monthlyProfit;
    }

    public void setMonthlyProfit(BigDecimal monthlyProfit) {
        this.monthlyProfit = monthlyProfit;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public Seller setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public UserEntity getEditUser() {
        return editUser;
    }

    public Seller setEditUser(UserEntity editUser) {
        this.editUser = editUser;
        return this;
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
}

package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import rbekyarov.car_dealership.models.entity.enums.Position;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "seller")
public class Seller extends BaseEntity {
    private String firstName;
    private String lastName;
    private Position position;
    private Set<Offer> offers;
    private Set<Sales> sales;
    private BigDecimal salary;
    private BigDecimal monthlyProfit;
    private BigDecimal totalProfit;
    private User author;

    private LocalDate dateCreate;

    public Seller() {
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    @OneToMany(mappedBy = "offer", targetEntity = Offer.class, fetch = FetchType.EAGER)
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
    @OneToMany(mappedBy = "sales", targetEntity = Sales.class, fetch = FetchType.EAGER)
    public Set<Sales> getSales() {
        return sales;
    }

    public void setSales(Set<Sales> sales) {
        this.sales = sales;
    }

    @Column
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Column
    public BigDecimal getMonthlyProfit() {
        return monthlyProfit;
    }

    public void setMonthlyProfit(BigDecimal monthlyProfit) {
        this.monthlyProfit = monthlyProfit;
    }

    @Column
    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
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
}

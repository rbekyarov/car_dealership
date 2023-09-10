package rbekyarov.car_dealership.models.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.StatusOffer;
import rbekyarov.car_dealership.models.entity.enums.StatusSalesInvoiced;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
    private Set<Car> cars;
    private Seller seller;
    private Client client;
    private Company company;
    private StatusSalesInvoiced statusSalesInvoiced;
    private Currency currency;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal totalPrice;
    private LocalDate dateCreate;
    private User author;
    private User editUser;
    private LocalDate dateEdite;
    public Sale() {
    }
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn( name = "sale_id", referencedColumnName = "id")
    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Enumerated(EnumType.STRING)
    public StatusSalesInvoiced getStatusSalesInvoiced() {
        return statusSalesInvoiced;
    }

    public void setStatusSalesInvoiced(StatusSalesInvoiced statusSalesInvoiced) {
        this.statusSalesInvoiced = statusSalesInvoiced;
    }
    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Column
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    @Column
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    @Column
    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
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

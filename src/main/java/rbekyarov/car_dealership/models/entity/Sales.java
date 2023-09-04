package rbekyarov.car_dealership.models.entity;


import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.StatusOffer;
import rbekyarov.car_dealership.models.entity.enums.StatusSalesInvoiced;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
@Entity
@Table(name = "sales")
public class Sales extends BaseEntity {
    private Set<Car> cars;
    private Seller seller;
    private Client client;
    private StatusSalesInvoiced statusSalesInvoiced;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal totalPrice;
    private LocalDate dateCreate;
    private User author;

    public Sales() {
    }
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "car_id", referencedColumnName = "id")
    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
    @ManyToOne
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
    @Enumerated(EnumType.STRING)
    public StatusSalesInvoiced getStatusSalesInvoiced() {
        return statusSalesInvoiced;
    }

    public void setStatusSalesInvoiced(StatusSalesInvoiced statusSalesInvoiced) {
        this.statusSalesInvoiced = statusSalesInvoiced;
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}

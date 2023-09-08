package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.ClientType;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client extends BaseEntity{
    private String name;
    private String vatOrId;
    private String email;
    private String phone;
    private String city;
    private String address;

    private ClientType clientType;


    private Set<Offer> offers;
    private Set<Sale> sales;
    private User author;
    private LocalDate dateCreate;
    private User editUser;
    private LocalDate dateEdite;
    public Client() {
    }
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column
    public String getVatOrId() {
        return vatOrId;
    }

    public void setVatOrId(String vatOrId) {
        this.vatOrId = vatOrId;
    }
    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Enumerated(EnumType.STRING)
    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "client_id", referencedColumnName = "id")
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "client_id", referencedColumnName = "id")
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
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

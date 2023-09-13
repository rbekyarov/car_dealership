package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vendors")
public class Vendor extends BaseEntity {
    @Column
    private String name;
    @Column
    private String country;
    @Column
    private String city;
    @Column
    private String address;
    @Column
    private String vatNumber;
    @Column
    private String email;
    @ManyToOne
    private UserEntity author;
    @Column
    private LocalDate dateCreate;
    @ManyToOne
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;

    public Vendor() {
    }

    public Vendor(String name, String vatNumber) {
        this.name = name;
        this.vatNumber = vatNumber;
    }

    public Vendor(String name, String country, String city, String address, String vatNumber, String email, UserEntity author, LocalDate dateCreate) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.vatNumber = vatNumber;
        this.email = email;
        this.author = author;
        this.dateCreate = dateCreate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Vendor setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public UserEntity getEditUser() {
        return editUser;
    }

    public Vendor setEditUser(UserEntity editUser) {
        this.editUser = editUser;
        return this;
    }
}


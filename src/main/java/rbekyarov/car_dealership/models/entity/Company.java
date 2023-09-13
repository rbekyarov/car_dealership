package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity {
    @Column
    private String name;
    @ManyToOne
    private Picture logoName;
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
    @Column
    private String managerName;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Set<BankAccount> bankAccounts;
    @ManyToOne
    private UserEntity author;
    @Column
    private LocalDate dateCreate;
    @ManyToOne
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;
    public Company() {
    }

    public Company(String name, String vatNumber) {
        this.name = name;
        this.vatNumber = vatNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Picture getLogoName() {
        return logoName;
    }

    public void setLogoName(Picture logoName) {
        this.logoName = logoName;
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


    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }



    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
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

    public Company setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public UserEntity getEditUser() {
        return editUser;
    }

    public Company setEditUser(UserEntity editUser) {
        this.editUser = editUser;
        return this;
    }
}

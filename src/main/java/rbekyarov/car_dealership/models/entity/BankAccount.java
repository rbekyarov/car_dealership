package rbekyarov.car_dealership.models.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "bank_accounts")
public class BankAccount extends BaseEntity{
    @Column
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"name","logoName","country","city","address","vatNumber","email","managerName","bankAccounts","author","dateCreate","editUser","dateEdite"})
    private Company company;
    @Column
    private String bankName;
    @Column
    private String accountNumber;
    @ManyToOne
    @JsonIgnoreProperties(value = {"exchangeRate","isMainCurrency","author","dateCreate","editUser","dateEdite"})

    private Currency currency;
    @Column
    private BigDecimal balance;
    @ManyToOne
    @JsonIgnoreProperties(value = {"firstName", "lastName", "email","roles", "position"})
    private UserEntity author;
    @Column
    private LocalDate dateCreate;
    @ManyToOne
    @JsonIgnoreProperties(value = {"firstName", "lastName", "email","roles", "position"})
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;
    public BankAccount() {
    }

    public BankAccount(String name, String bankName, String accountNumber, Currency currency, BigDecimal balance) {
        this.name = name;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = new BigDecimal(0.0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public BankAccount setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public UserEntity getEditUser() {
        return editUser;
    }

    public BankAccount setEditUser(UserEntity editUser) {
        this.editUser = editUser;
        return this;
    }
}

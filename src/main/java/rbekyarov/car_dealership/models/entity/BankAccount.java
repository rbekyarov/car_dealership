package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.Currency;

import java.math.BigDecimal;
@Entity
@Table(name = "bank_accounts")
public class BankAccount extends BaseEntity{
    private String bankName;
    private String accountNumber;
    private Currency currency;
    private BigDecimal balance;

    public BankAccount() {
    }

//    public BankAccount(String bankName, String bankNumber, Currency currency, BigDecimal balance) {
//        this.bankName = bankName;
//        this.bankNumber = bankNumber;
//        this.currency = currency;
//        this.balance = new BigDecimal(0.0);
//    }

    @Column(name = "bank_name", nullable = false)
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    @Column(name = "account_number", nullable = false)
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String bankNumber) {
        this.accountNumber = bankNumber;
    }
    @Enumerated(EnumType.STRING)
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    @Column
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

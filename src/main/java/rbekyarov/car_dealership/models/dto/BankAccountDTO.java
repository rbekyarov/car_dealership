package rbekyarov.car_dealership.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import rbekyarov.car_dealership.models.entity.enums.Currency;

import java.math.BigDecimal;

public class BankAccountDTO {
    private Long id;
    private String name;
    private String bankName;
    private String accountNumber;
    private Currency currency;
    private BigDecimal balance;

    public BankAccountDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Size(min = 2, max = 20, message = "Content length must be between 2 and 20 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Size(min = 2, max = 20, message = "Content length must be between 2 and 20 characters!")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    @Size(min = 2, max = 20, message = "Content length must be between 2 and 20 characters!")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    @NotNull(message = "Field cannot be empty")
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
}

package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import rbekyarov.car_dealership.models.entity.enums.CancellationInvoice;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice {
    private String companyName;
    private String companyCityName;
    private String companyAddress;
    private String companyVatNumber;
    private String companyEmail;
    private String companyBankName;
    private String companyBankAccount;
    private String companyManagerName;

    private String carName;
    private String carVinNumber;
    private String carRegDate;

    private String clientName;
    private String clientEmail;
    private String clientAddress;
    private String clientCityName;
    private String clientPhone;

    private BigDecimal price;
    private Double discount;
    private BigDecimal totalPrice;

    private String authorName;
    private LocalDate dateCreate;

    private CancellationInvoice cancellationInvoice;
    private LocalDate cancelledDateInvoice;

    public Invoice() {
    }
    @Column
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @Column
    public String getCompanyCityName() {
        return companyCityName;
    }

    public void setCompanyCityName(String companyCityName) {
        this.companyCityName = companyCityName;
    }
    @Column
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    @Column
    public String getCompanyVatNumber() {
        return companyVatNumber;
    }

    public void setCompanyVatNumber(String companyVatNumber) {
        this.companyVatNumber = companyVatNumber;
    }
    @Column
    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
    @Column
    public String getCompanyBankName() {
        return companyBankName;
    }

    public void setCompanyBankName(String companyBankName) {
        this.companyBankName = companyBankName;
    }
    @Column
    public String getCompanyBankAccount() {
        return companyBankAccount;
    }

    public void setCompanyBankAccount(String companyBankAccount) {
        this.companyBankAccount = companyBankAccount;
    }
    @Column
    public String getCompanyManagerName() {
        return companyManagerName;
    }

    public void setCompanyManagerName(String companyManagerName) {
        this.companyManagerName = companyManagerName;
    }
    @Column
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
    @Column
    public String getCarVinNumber() {
        return carVinNumber;
    }

    public void setCarVinNumber(String carVinNumber) {
        this.carVinNumber = carVinNumber;
    }
    @Column
    public String getCarRegDate() {
        return carRegDate;
    }

    public void setCarRegDate(String carRegDate) {
        this.carRegDate = carRegDate;
    }
    @Column
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    @Column
    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
    @Column
    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }
    @Column
    public String getClientCityName() {
        return clientCityName;
    }

    public void setClientCityName(String clientCityName) {
        this.clientCityName = clientCityName;
    }
    @Column
    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }
    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Column
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
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
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    @Column
    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }
    @Enumerated(EnumType.STRING)
    public CancellationInvoice getCancellationInvoice() {
        return cancellationInvoice;
    }

    public void setCancellationInvoice(CancellationInvoice cancellationInvoice) {
        this.cancellationInvoice = cancellationInvoice;
    }
    @Column
    public LocalDate getCancelledDateInvoice() {
        return cancelledDateInvoice;
    }

    public void setCancelledDateInvoice(LocalDate cancelledDateInvoice) {
        this.cancelledDateInvoice = cancelledDateInvoice;
    }
}

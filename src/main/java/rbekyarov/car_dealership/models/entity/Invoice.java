package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.CancellationInvoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {
    @Column
    private String companyName;
    @Column
    private String companyCityName;
    @Column
    private String companyAddress;
    @Column
    private String companyVatNumber;
    @Column
    private String companyEmail;
    @Column
    private String companyBankName;
    @Column
    private String companyBankAccount;
    @Column
    private String companyManagerName;
    @Column
    private String currencyCode;
    @Column
    private Long bankAccountId;
    @Column
    private Long saleId;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn( name = "invoice_id", referencedColumnName = "id")
    private Set<CarInvoiced> carInvoiced;
    @Column
    private String clientName;
    @Column
    private String clientEmail;
    @Column
    private String clientAddress;
    @Column
    private String clientCityName;
    @Column
    private String clientPhone;
    @Column
    private BigDecimal price;
    @Column
    private Double discount;
    @Column
    private BigDecimal totalPrice;
    @Column
    private String authorName;
    @Column
    private String cancellationUserName;
    @Column
    private LocalDate dateCreate;
    @Enumerated(EnumType.STRING)
    private CancellationInvoice cancellationInvoice;
    @Column
    private LocalDate cancelledDateInvoice;

    public Invoice() {
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCityName() {
        return companyCityName;
    }

    public void setCompanyCityName(String companyCityName) {
        this.companyCityName = companyCityName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }


    public String getCompanyVatNumber() {
        return companyVatNumber;
    }

    public void setCompanyVatNumber(String companyVatNumber) {
        this.companyVatNumber = companyVatNumber;
    }


    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }


    public String getCompanyBankName() {
        return companyBankName;
    }

    public void setCompanyBankName(String companyBankName) {
        this.companyBankName = companyBankName;
    }

    public String getCompanyBankAccount() {
        return companyBankAccount;
    }

    public void setCompanyBankAccount(String companyBankAccount) {
        this.companyBankAccount = companyBankAccount;
    }

    public String getCompanyManagerName() {
        return companyManagerName;
    }

    public void setCompanyManagerName(String companyManagerName) {
        this.companyManagerName = companyManagerName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }


    public Set<CarInvoiced> getCarInvoiced() {
        return carInvoiced;
    }

    public void setCarInvoiced(Set<CarInvoiced> carInvoiced) {
        this.carInvoiced = carInvoiced;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }


    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientCityName() {
        return clientCityName;
    }

    public void setClientCityName(String clientCityName) {
        this.clientCityName = clientCityName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }


    public CancellationInvoice getCancellationInvoice() {
        return cancellationInvoice;
    }

    public void setCancellationInvoice(CancellationInvoice cancellationInvoice) {
        this.cancellationInvoice = cancellationInvoice;
    }

    public LocalDate getCancelledDateInvoice() {
        return cancelledDateInvoice;
    }

    public void setCancelledDateInvoice(LocalDate cancelledDateInvoice) {
        this.cancelledDateInvoice = cancelledDateInvoice;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public String getCancellationUserName() {
        return cancellationUserName;
    }

    public void setCancellationUserName(String cancellationUserName) {
        this.cancellationUserName = cancellationUserName;
    }
}

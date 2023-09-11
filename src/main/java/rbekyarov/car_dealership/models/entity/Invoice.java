package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.CancellationInvoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {
    private String companyName;
    private String companyCityName;
    private String companyAddress;
    private String companyVatNumber;
    private String companyEmail;
    private String companyBankName;
    private String companyBankAccount;
    private String companyManagerName;

    private String currencyCode;
    private Long bankAccountId;
    private Long saleId;
    private Set<CarInvoiced> carInvoiced;

    private String clientName;
    private String clientEmail;
    private String clientAddress;
    private String clientCityName;
    private String clientPhone;

    private BigDecimal price;
    private Double discount;
    private BigDecimal totalPrice;

    private String authorName;
    private String cancellationUserName;
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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Column
    public Long getBankAccountId() {
        return bankAccountId;
    }
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn( name = "invoice_id", referencedColumnName = "id")
    public Set<CarInvoiced> getCarInvoiced() {
        return carInvoiced;
    }

    public void setCarInvoiced(Set<CarInvoiced> carInvoiced) {
        this.carInvoiced = carInvoiced;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
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
    @Column
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

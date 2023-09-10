package rbekyarov.car_dealership.models.dto;

import jakarta.validation.constraints.NotNull;
import rbekyarov.car_dealership.models.entity.Currency;
import rbekyarov.car_dealership.models.entity.Vendor;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CostDTO {
    private Long id;
    private Long carId;
    private Long vendorId;
    private String description;
    private String invoiceNo;
    private BigDecimal amount;
    private LocalDate dateCost;

    public CostDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    @NotNull
    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDateCost() {
        return dateCost;
    }

    public void setDateCost(LocalDate dateCost) {
        this.dateCost = dateCost;
    }
}

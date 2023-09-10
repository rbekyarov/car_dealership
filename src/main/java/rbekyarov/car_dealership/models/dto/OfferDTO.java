package rbekyarov.car_dealership.models.dto;


import jakarta.validation.constraints.NotNull;
import rbekyarov.car_dealership.models.entity.enums.StatusOffer;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class OfferDTO {
    private Set<Long> carIds;
    private Long sellerId;
    private Long clientId;
    private Long companyId;
    private StatusOffer statusOffer;
    private BigDecimal discount;

    public OfferDTO() {
    }
@NotNull
    public Set<Long> getCarIds() {
        return carIds;
    }

    public void setCarIds(Set<Long> carIds) {
        this.carIds = carIds;
    }



    @NotNull
    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
    @NotNull
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getCompanyId() {
        return companyId;
    }


    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @NotNull
    public StatusOffer getStatusOffer() {
        return statusOffer;
    }

    public void setStatusOffer(StatusOffer statusOffer) {
        this.statusOffer = statusOffer;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}

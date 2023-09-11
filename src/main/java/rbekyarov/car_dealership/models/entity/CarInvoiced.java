package rbekyarov.car_dealership.models.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import rbekyarov.car_dealership.models.entity.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "car_invoiced")
public class CarInvoiced extends BaseEntity {
    private String carName;
    private String carVinNumber;
    private LocalDate carRegDate;

    public CarInvoiced() {
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarVinNumber() {
        return carVinNumber;
    }

    public void setCarVinNumber(String carVinNumber) {
        this.carVinNumber = carVinNumber;
    }

    public LocalDate getCarRegDate() {
        return carRegDate;
    }

    public void setCarRegDate(LocalDate carRegDate) {
        this.carRegDate = carRegDate;
    }
}

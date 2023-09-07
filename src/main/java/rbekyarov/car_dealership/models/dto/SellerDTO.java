package rbekyarov.car_dealership.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import rbekyarov.car_dealership.models.entity.enums.Position;
import java.math.BigDecimal;

public class SellerDTO {
    private String firstName;
    private String lastName;
    private Position position;
    private BigDecimal salary;

    public SellerDTO() {
    }

    public SellerDTO(String firstName) {
        this.firstName = firstName;
    }

   // @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

   // @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   // @NotNull
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

   // @Positive
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}

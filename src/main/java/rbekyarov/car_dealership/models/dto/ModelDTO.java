package rbekyarov.car_dealership.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.User;

import java.time.LocalDate;

public class ModelDTO {
    private Long id;
    private String name;
    private Long brandId;

    public ModelDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @NotNull(message = "Field cannot be empty")
    @Size(min = 2, max = 20, message = "Content length must be between 2 and 20 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull(message = "Field cannot be empty")
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}

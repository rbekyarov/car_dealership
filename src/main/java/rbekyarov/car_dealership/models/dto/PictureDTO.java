package rbekyarov.car_dealership.models.dto;

import jakarta.validation.constraints.Size;

public class PictureDTO {
    private Long id;
    private String name;

    public PictureDTO() {
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
}

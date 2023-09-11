package rbekyarov.car_dealership.models.dto;

import jakarta.validation.constraints.NotNull;

public class CompanyDTO {
    private Long id;
    private String name;

    private Long pictureId;
    private String country;
    private String city;
    private String address;
    private String vatNumber;
    private String email;
    private String managerName;

    public CompanyDTO() {
    }

    public CompanyDTO(String name, String country, String city, String address, String vatNumber, String email, String managerName) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.vatNumber = vatNumber;
        this.email = email;
        this.managerName = managerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @NotNull(message = "Field cannot be empty")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @NotNull(message = "Field cannot be empty")
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}

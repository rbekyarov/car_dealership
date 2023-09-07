package rbekyarov.car_dealership.models.dto;

import rbekyarov.car_dealership.models.entity.enums.ClientType;

public class ClientDTO {
    private Long id;
    private String name;
    private String vatOrId;
    private String email;
    private String phone;
    private String city;
    private String address;

    private ClientType clientType;

    public ClientDTO() {
    }

    public ClientDTO(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVatOrId() {
        return vatOrId;
    }

    public void setVatOrId(String vatOrId) {
        this.vatOrId = vatOrId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}

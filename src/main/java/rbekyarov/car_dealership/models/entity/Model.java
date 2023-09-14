package rbekyarov.car_dealership.models.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "models")
public class Model extends BaseEntity{
    @Column
    private String name;
    @ManyToOne
    @JsonIgnoreProperties(value = {"author", "dateCreate", "editUser","dateEdite"})

    private Brand brand;
    @ManyToOne
    @JsonIgnoreProperties(value = {"firstName", "lastName", "email","roles", "position"})
    private UserEntity author;
    @Column
    private LocalDate dateCreate;
    @ManyToOne
    @JsonIgnoreProperties(value = {"firstName", "lastName", "email","roles", "position"})
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;

    public Model() {
    }
    public Model(String name, Brand brand) {
        this.name = name;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }


    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDate getDateEdite() {
        return dateEdite;
    }

    public void setDateEdite(LocalDate dateEdite) {
        this.dateEdite = dateEdite;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public Model setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
    public UserEntity getEditUser() {
        return editUser;
    }

    public Model setEditUser(UserEntity editUser) {
        this.editUser = editUser;
        return this;
    }
}

package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {
    @Column
    private String name;
    @ManyToOne
    private UserEntity author;
    @Column
    private LocalDate dateCreate;
    @ManyToOne
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;
    @ManyToOne
    private Car car;

    public Picture() {
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
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
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public Picture setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
    public UserEntity getEditUser() {
        return editUser;
    }

    public Picture setEditUser(UserEntity editUser) {
        this.editUser = editUser;
        return this;
    }
}

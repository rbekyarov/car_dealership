package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
    @ManyToOne
    private UserEntity author;
    @Column
    private LocalDate dateCreate;
    @ManyToOne
    private UserEntity editUser;
    @Column
    private LocalDate dateEdite;
    public Brand() {
    }

    public Brand(String name) {
        this.name = name;
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

    public UserEntity getAuthor() {
        return author;
    }

    public Brand setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public UserEntity getEditUser() {
        return editUser;
    }

    public Brand setEditUser(UserEntity editUser) {
        this.editUser = editUser;
        return this;
    }
}

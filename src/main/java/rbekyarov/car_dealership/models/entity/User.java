package rbekyarov.car_dealership.models.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import rbekyarov.car_dealership.models.entity.enums.Role;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;

    private String email;
    private Role role;

    public User() {
    }

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Column(name = "user_name", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Column (name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column (name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}


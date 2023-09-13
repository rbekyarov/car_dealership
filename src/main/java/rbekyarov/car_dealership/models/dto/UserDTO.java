package rbekyarov.car_dealership.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import rbekyarov.car_dealership.models.entity.Role;
import rbekyarov.car_dealership.models.entity.enums.Position;

public class UserDTO {
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @NotNull
    private String username;
    private String firstName;
    private String lastName;
    @NotNull
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
    @NotNull()
    private String repeatPassword;
    @Email
    @NotEmpty(message = "Email Cannot be empty")
    private String email;
    private Long roleId;
    private Position position;

    public UserDTO() {
    }

    public UserDTO(String username, String firstName, String lastName, String password, String repeatPassword, String email, Long roleId, Position position) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
        this.roleId = roleId;
        this.position = position;
    }

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public UserDTO setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }

    public UserDTO setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

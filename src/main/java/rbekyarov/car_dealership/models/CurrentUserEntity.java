package rbekyarov.car_dealership.models;

public class CurrentUserEntity {
    private String username;

    public CurrentUserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public CurrentUserEntity setUsername(String username) {
        this.username = username;
        return this;
    }
}

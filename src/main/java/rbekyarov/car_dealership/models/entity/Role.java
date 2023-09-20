package rbekyarov.car_dealership.models.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import rbekyarov.car_dealership.models.ERole;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    public ERole getName() {
        return name;
    }

    public Role setName(ERole name) {
        this.name = name;
        return this;
    }
}
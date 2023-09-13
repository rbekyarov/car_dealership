package rbekyarov.car_dealership.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    List<Role> findAll();

    Optional<Role> findRoleByName(String name);





}

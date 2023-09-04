package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.models.entity.enums.Position;
import rbekyarov.car_dealership.models.entity.enums.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("select u from User as u order by u.id asc ")
    List<User> findAllUserById();

    Optional<User> findById(Long id);

    @Transactional
    @Modifying
    @Query("update User as u SET u.email=:email,u.role=:role, u.position=:position where u.id=:id ")
    void editUser(@Param("email") String email,
                  @Param("role") Role role,
                  @Param("position") Position position,
                  @Param("id") Long id);
    @Transactional
    @Modifying
    @Query("update User as u SET u.password=:password, u.username=:username where u.id=:id ")
    void editUserPassword(@Param("password") String password,
                          @Param("id") Long id,
                          @Param("username") String username);
}

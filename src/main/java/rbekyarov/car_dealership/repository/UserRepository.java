package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.models.entity.enums.Position;
import rbekyarov.car_dealership.models.entity.enums.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);


    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    UserEntity getUserByEmail(@Param("email") String email);

    Optional<UserEntity> findByUsername(String username);
    UserEntity getUserEntityByUsername(String username);

    UserEntity getUsersById(Long id);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);


    Optional<UserEntity> findUserEntityByEmail(String email);

    @Transactional
    @Modifying
    @Query("update UserEntity as u SET  u.username=:username where u.id=:id ")
    void editUsername(@Param("id") Long id,
                      @Param("username") String username);

    @Transactional
    @Modifying
    @Query("update UserEntity as u SET  u.password=:password where u.id=:id ")
    void editUserPassword(@Param("id") Long id,
                          @Param("password") String password);

    @Query("select u from UserEntity as u order by u.id asc ")
    List<UserEntity> findAllUserById();

    Object findByEmailOrUsername(String email, String username);

}

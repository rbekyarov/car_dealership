package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Client;
import rbekyarov.car_dealership.models.entity.enums.ClientType;

import java.time.LocalDate;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Transactional
    @Modifying
    @Query("update Client as c SET c.name = :name,c.vatOrId = :vatOrId,c.email = :email,c.phone = :phone,c.city = :city,c.address = :address,c.clientType = :clientType, c.editUser.id=:editUserId,c.dateEdite=:dateEdit where c.id=:id ")
    void editClient(
            @Param("name") String name,
            @Param("vatOrId") String vatOrId,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("city") String city,
            @Param("address") String address,
            @Param("clientType") ClientType clientType,
            @Param("id") Long id,
            @Param("editUserId") Long editUserId,
            @Param("dateEdit") LocalDate dateEdit);
}

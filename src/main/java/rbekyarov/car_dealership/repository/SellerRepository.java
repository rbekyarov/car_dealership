package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Seller;
import rbekyarov.car_dealership.models.entity.enums.Position;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Transactional
    @Modifying
    @Query("update Seller as s SET s.firstName = :firstName,s.lastName = :lastName,s.position = :position,s.salary = :salary, s.author.id=:editAuthorId,s.dateCreate=:dateEdit where s.id=:id ")
    void editSeller(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("position") Position position,
            @Param("salary") BigDecimal salary,
            @Param("id") Long id,
            @Param("editAuthorId") Long editAuthorId,
            @Param("dateEdit") LocalDate dateEdit);
}

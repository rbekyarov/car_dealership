package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Cost;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface CostRepository extends JpaRepository<Cost, Long> {

    @Transactional
    @Modifying
    @Query("update Cost as c SET c.vendor.id = :vendorId, c.description = :description,c.invoiceNo = :invoiceNo,c.amount = :amount,c.dateCost = :dateCost, c.author.id=:editAuthorId, c.dateCreate=:dateEdit where c.id=:id ")
    void editCost(
            @Param("vendorId") Long vendorId,
                   @Param("description") String description,
                   @Param("invoiceNo") String invoiceNo,
                   @Param("amount") BigDecimal amount,
                   @Param("dateCost") LocalDate dateCost,
                   @Param("id") Long id ,
                   @Param("editAuthorId") Long editAuthorId,
                   @Param("dateEdit") LocalDate dateEdit);
}

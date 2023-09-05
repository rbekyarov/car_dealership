package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.PricingPercentData;

import java.time.LocalDate;

@Repository
public interface PricingPercentDataRepository extends JpaRepository<PricingPercentData, Long> {

    @Transactional
    @Modifying
    @Query("update PricingPercentData as p SET p.percentSaleCar = :percentSaleCar,p.percentSaleCarMin = :percentSaleCarMin,p.percentCommission = :percentCommission, p.author.id=:editAuthorId,p.dateCreate=:dateEdit where p.id=:id ")
    void editPricingPercentData(
            @Param("percentSaleCar") int percentSaleCar,
            @Param("percentSaleCarMin") int percentSaleCarMin,
            @Param("percentCommission") int percentCommission,
                   @Param("id") Long id ,
                   @Param("editAuthorId") Long editAuthorId,
                   @Param("dateEdit") LocalDate dateEdit);
}

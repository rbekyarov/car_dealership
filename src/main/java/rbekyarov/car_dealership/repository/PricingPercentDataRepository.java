package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.PricingPercentData;
import rbekyarov.car_dealership.models.entity.enums.ActivePricingPercentData;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PricingPercentDataRepository extends JpaRepository<PricingPercentData, Long> {

    @Transactional
    @Modifying
    @Query("update PricingPercentData as p SET p.percentSaleCar = :percentSaleCar,p.percentSaleCarMin = :percentSaleCarMin,p.percentCommission = :percentCommission,p.activePricingPercentData = :activePricingPercentData,p.percentVAT = :percentVAT, p.editUser.id=:editUserId,p.dateEdite=:dateEdit\n where p.id=:id ")
    void editPricingPercentData(
            @Param("percentSaleCar") int percentSaleCar,
            @Param("percentSaleCarMin") int percentSaleCarMin,
            @Param("percentCommission") int percentCommission,
            @Param("activePricingPercentData") ActivePricingPercentData activePricingPercentData,
            @Param("percentVAT") int percentVAT,
            @Param("id") Long id,
            @Param("editUserId") Long editUserId,
            @Param("dateEdit") LocalDate dateEdit);

    @Transactional
    @Modifying
    @Query("update PricingPercentData as p SET p.activePricingPercentData = 'NO'")
    void setAllActivePricingPercentDataToNO();

    @Query("select p from PricingPercentData as p where p.activePricingPercentData = 'YES' ")
    Optional<PricingPercentData> findActivePricingPercentData();
}

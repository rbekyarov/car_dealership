package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Offer;
import rbekyarov.car_dealership.models.entity.enums.StatusOffer;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {


    @Transactional
    @Modifying
    @Query("update Offer as o SET o.price=:price,o.totalPrice=:totalPrice,o.discount=:discount,o.statusOffer=:statusOffer,o.client.id=:clientId,o.seller.id=:sellerId,o.editUser.id=:editUserId,o.dateEdite=:dateEdit,o.company.id=:companyId  where o.id=:id ")
    void editOffer(@Param("price") BigDecimal price,
                   @Param("totalPrice") BigDecimal totalPrice,
                   @Param("discount") BigDecimal discount,
                   @Param("statusOffer") StatusOffer statusOffer,
                   @Param("clientId") Long clientId,
                   @Param("sellerId") Long sellerId,
                   @Param("id") Long id,
                   @Param("editUserId") Long editUserId,
                   @Param("dateEdit") LocalDate dateEdit,
                   @Param("companyId") Long companyId

    );


}

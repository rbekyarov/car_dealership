package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Sale;
import rbekyarov.car_dealership.models.entity.enums.StatusOffer;
import rbekyarov.car_dealership.models.entity.enums.StatusSalesInvoiced;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Transactional
    @Modifying
    @Query("update Sale as s SET s.price=:price,s.totalPrice=:totalPrice,s.discount=:discount,s.statusSalesInvoiced=:statusSalesInvoiced,s.client.id=:clientId,s.seller.id=:sellerId,s.editUser.id=:editUserId,s.dateEdite=:dateEdit  where s.id=:id ")
    void editSale(@Param("price") BigDecimal price,
                   @Param("totalPrice") BigDecimal totalPrice,
                   @Param("discount") BigDecimal discount,
                   @Param("statusSalesInvoiced") StatusSalesInvoiced statusSalesInvoiced,
                   @Param("clientId") Long clientId,
                   @Param("sellerId") Long sellerId,
                   @Param("id") Long id,
                   @Param("editUserId") Long editUserId,
                   @Param("dateEdit") LocalDate dateEdit

    );

}

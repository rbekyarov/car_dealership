package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Invoice;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface  InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("select i from Invoice as i where i.cancellationInvoice='NO' order by i.id desc ")
    List<Invoice> findAllRealInvoice();

    @Query("select i from Invoice as i where i.cancellationInvoice='YES' order by i.id desc ")
    List<Invoice> findAllCancelledInvoice();

    @Transactional
    @Modifying
    @Query("update Invoice as i SET i.cancellationInvoice='YES' where i.id=:id ")
    void setCanceledOnInvoiced( @Param("id") Long id);
    @Query("select i from Invoice as i where i.clientEmail=:clientEmail")
    List<Invoice> listInvoiceByEmail(@Param("clientEmail")String clientEmail);
    @Query("select i from Invoice as i where i.id=:invoiceNumber")
    List<Invoice> listInvoiceById( @Param("invoiceNumber")Long invoiceNumber);
    @Transactional
    @Modifying
    @Query("update Invoice as i SET i.cancellationUserName=:username where i.id=:id ")
    void setCancellationUserName(@Param("username")String username, @Param("id")Long id);
    @Transactional
    @Modifying
    @Query("update Invoice as i SET i.cancelledDateInvoice=:canceledDate where i.id=:id ")
    void setDateCancelation(@Param("canceledDate")LocalDate canceledDate, Long id);
}

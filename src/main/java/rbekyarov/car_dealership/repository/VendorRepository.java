package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Vendor;

import java.time.LocalDate;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    @Transactional
    @Modifying
    @Query("update Vendor as v SET v.name = :name,v.country = :country,v.city = :city,v.address = :address,v.vatNumber = :vatNumber,v.email = :email, v.author.id=:editAuthorId,v.dateCreate=:dateEdit where v.id=:id ")
    void editVendor(
            @Param("name") String name,
            @Param("country") String country,
            @Param("city") String city,
            @Param("address") String address,
            @Param("vatNumber") String vatNumber,
            @Param("email") String email,
            @Param("id") Long id,
            @Param("editAuthorId") Long editAuthorId,
            @Param("dateEdit") LocalDate dateEdit);

}

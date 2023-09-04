package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.BankAccount;
import rbekyarov.car_dealership.models.entity.Company;
import rbekyarov.car_dealership.models.entity.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Transactional
    @Modifying
    @Query("update Company as c SET c.name = :name,c.logoName = :pictureId,c.country = :country,c.city = :city,c.address = :address, c.vatNumber=:vatNumber,c.email=:email,c.managerName=:managerName,c.author.id=:editAuthorId,c.dateCreate=:dateEdit where c.id=:id ")
    void editCompany(
            @Param("name") String name,
            @Param("pictureId") Long pictureId,
            @Param("country") String country,
            @Param("city") String city,
            @Param("address") String address,
            @Param("vatNumber") String vatNumber,
            @Param("email") String email,
            @Param("managerName") String managerName,
            @Param("id") Long id,
            @Param("editAuthorId") Long editAuthorId,
            @Param("dateEdit") LocalDate dateEdit);

}

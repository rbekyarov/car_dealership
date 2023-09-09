package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Currency;
import rbekyarov.car_dealership.models.entity.enums.IsMainCurrency;

import java.time.LocalDate;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Transactional
    @Modifying
    @Query("update Currency as c SET c.code = :code,c.name = :name,c.exchangeRate = :exchangeRate,c.isMainCurrency = :isMainCurrency, c.editUser.id=:editUserId,c.dateEdite=:dateEdit where c.id=:id ")
    void editCurrency(
            @Param("code") String code,
            @Param("name") String name,
            @Param("exchangeRate") double exchangeRate,
            @Param("isMainCurrency") IsMainCurrency isMainCurrency,
            @Param("id") Long id ,
            @Param("editUserId") Long editUserId,
            @Param("dateEdit") LocalDate dateEdit);

    @Transactional
    @Modifying
    @Query("update Currency as c SET c.isMainCurrency = 'NO' where c.id=:id ")
    void updateCurrencyIsMain(@Param("id") Long id);
}

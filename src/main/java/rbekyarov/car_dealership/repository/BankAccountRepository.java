package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.BankAccount;
import rbekyarov.car_dealership.models.entity.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {


    @Transactional
    @Modifying
    @Query("update BankAccount as b SET b.name = :name,b.bankName = :bankName,b.accountNumber = :accountNumber,b.currency = :currency,b.balance = :balance, b.author.id=:editAuthorId,b.dateCreate=:dateEdit where b.id=:id ")
    void editBankAccount(
            @Param("name") String name,
            @Param("bankName") String bankName,
            @Param("accountNumber") String accountNumber,
            @Param("currency") Currency currency,
            @Param("balance") BigDecimal balance,
            @Param("id") Long id,
            @Param("editAuthorId") Long editAuthorId,
            @Param("dateEdit") LocalDate dateEdit);
}

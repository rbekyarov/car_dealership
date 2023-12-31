package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.BankAccount;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {


    @Transactional
    @Modifying
    @Query("update BankAccount as b SET b.name = :name,b.bankName = :bankName,b.accountNumber = :accountNumber,b.currency.id = :currencyId,b.balance = :balance, b.editUser.id=:editUserId,b.dateEdite=:dateEdit where b.id=:id ")
    void editBankAccount(
            @Param("name") String name,
            @Param("bankName") String bankName,
            @Param("accountNumber") String accountNumber,
            @Param("currencyId") Long currencyId,
            @Param("balance") BigDecimal balance,
            @Param("id") Long id,
            @Param("editUserId") Long editUserId,
            @Param("dateEdit") LocalDate dateEdit);
    @Query("select b.balance from BankAccount as b where b.id=:bankAccountId")
    BigDecimal getCurrentBalance(Long bankAccountId);
    @Transactional
    @Modifying
    @Query("update BankAccount as b SET b.balance = :amount where b.id=:bankAccountId ")
    void editBalance(@Param("amount") BigDecimal amount,@Param("bankAccountId") Long bankAccountId);
    @Transactional
    @Modifying
    @Query("update BankAccount as b SET b.balance = :balance where b.id=:id ")
    void updateBalance(@Param("balance") BigDecimal balance, @Param("id")Long id);
}

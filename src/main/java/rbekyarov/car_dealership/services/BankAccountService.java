package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.BankAccountDTO;
import rbekyarov.car_dealership.models.entity.BankAccount;
import rbekyarov.car_dealership.models.entity.enums.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BankAccountService {
    List<BankAccount> findAllBankAccounts();

    void addBankAccount(BankAccountDTO bankAccountDTO, HttpSession session);

    void removeBankAccountById(Long id);

    Optional<BankAccount> findById(Long id);

    void editBankAccount(String name,
                         String bankName,
                         String accountNumber,
                         Currency currency,
                         BigDecimal balance,
                         Long id,
                         HttpSession session);

    BigDecimal getCurrentBalance(Long bankAccountId);

    void editBalance(BigDecimal amount, Long bankAccountId);
}

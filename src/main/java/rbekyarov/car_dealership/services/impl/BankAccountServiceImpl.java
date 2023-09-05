package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.BankAccountDTO;
import rbekyarov.car_dealership.models.entity.BankAccount;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.models.entity.enums.Currency;
import rbekyarov.car_dealership.repository.BankAccountRepository;
import rbekyarov.car_dealership.services.BankAccountService;
import rbekyarov.car_dealership.services.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ModelMapper modelMapper, UserService userService) {
        this.bankAccountRepository = bankAccountRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<BankAccount> findAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public void addBankAccount(BankAccountDTO bankAccountDTO, HttpSession session) {
        BankAccount bankAccount = modelMapper.map(bankAccountDTO, BankAccount.class);
        //get and set Author
        bankAccount.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        bankAccount.setDateCreate(LocalDate.now());
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void removeBankAccountById(Long id) {
        bankAccountRepository.deleteById(id);
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public void editBankAccount(String name, String bankName, String accountNumber, Currency currency, BigDecimal balance, Long id, HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        bankAccountRepository.editBankAccount(name, bankName, accountNumber, currency, balance, id, editAuthorId, dateEdit);
    }

    @Override
    public BigDecimal getCurrentBalance(Long bankAccountId) {
        return bankAccountRepository.getCurrentBalance(bankAccountId);
    }

    @Override
    public void editBalance(BigDecimal amount, Long bankAccountId) {
        bankAccountRepository.editBalance(amount,bankAccountId);
    }
}
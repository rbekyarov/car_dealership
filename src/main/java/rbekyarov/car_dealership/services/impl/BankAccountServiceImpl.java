package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.BankAccountDTO;
import rbekyarov.car_dealership.models.entity.BankAccount;
import rbekyarov.car_dealership.models.entity.Currency;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.repository.BankAccountRepository;
import rbekyarov.car_dealership.services.BankAccountService;
import rbekyarov.car_dealership.services.CompanyService;
import rbekyarov.car_dealership.services.CurrencyService;
import rbekyarov.car_dealership.services.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final CompanyService companyService;
    private final CurrencyService currencyService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, CompanyService companyService, CurrencyService currencyService, ModelMapper modelMapper, UserService userService) {
        this.bankAccountRepository = bankAccountRepository;
        this.companyService = companyService;
        this.currencyService = currencyService;
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
        //SET COMPANY
        bankAccount.setCompany(companyService.findById(bankAccountDTO.getCompanyId()).get());
        //SET CURRENCY
        bankAccount.setCurrency(currencyService.findById(bankAccountDTO.getCurrencyId()).get());
        //get and set Author
        //bankAccount.setAuthor(userService.getAuthorFromSession(session));
        bankAccount.setAuthor(userService.findById(1L).get());
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
    public void editBankAccount(String name, String bankName, String accountNumber, Long currencyId, BigDecimal balance, Long id, HttpSession session) {
        User editUser = userService.getAuthorFromSession(session);
        Long editUserId = editUser.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        bankAccountRepository.editBankAccount(name, bankName, accountNumber, currencyId, balance, id, editUserId, dateEdit);
    }

    @Override
    public BigDecimal getCurrentBalance(Long bankAccountId) {
        return bankAccountRepository.getCurrentBalance(bankAccountId);
    }

    @Override
    public void editBalance(BigDecimal amount, Long bankAccountId) {
        bankAccountRepository.editBalance(amount,bankAccountId);
    }

    @Override
    public void updateBalance(BigDecimal balance, Long id) {
        bankAccountRepository.updateBalance(balance,id);
    }
}
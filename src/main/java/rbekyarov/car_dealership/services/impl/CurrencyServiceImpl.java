package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.CurrencyDTO;
import rbekyarov.car_dealership.models.entity.Currency;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.models.entity.enums.IsMainCurrency;
import rbekyarov.car_dealership.repository.CurrencyRepository;
import rbekyarov.car_dealership.services.CurrencyService;
import rbekyarov.car_dealership.services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, ModelMapper modelMapper, UserService userService) {
        this.currencyRepository = currencyRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<Currency> findAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public void addCurrency(CurrencyDTO currencyDTO, HttpSession session) {
        Currency currency = modelMapper.map(currencyDTO, Currency.class);
        //get and set Author
        // currency.setAuthor(userService.getAuthorFromSession(session));
        currency.setAuthor(userService.findById(1L).get());
        // set dateCreated
        currency.setDateCreate(LocalDate.now());

        if (currencyDTO.getIsMainCurrency().name().equals("YES")) {
            List<Currency> allCurrencies = currencyRepository.findAll();
            if (allCurrencies.size() > 0) {
                for (Currency allCurrency : allCurrencies) {
                    currencyRepository.updateCurrencyIsMain(allCurrency.getId());
                }
            }
        }
        currencyRepository.save(currency);
    }

    @Override
    public void removeCurrencyById(Long id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public Optional<Currency> findById(Long id) {
        return currencyRepository.findById(id);
    }

    @Override
    public void editCurrency(CurrencyDTO currencyDTO, Long id, HttpSession session) {
        String code = currencyDTO.getCode();
        String name = currencyDTO.getName();
        double exchangeRate = currencyDTO.getExchangeRate();
        IsMainCurrency isMainCurrency = currencyDTO.getIsMainCurrency();

        User editUser = userService.getAuthorFromSession(session);
        Long editUserId = editUser.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        if (currencyDTO.getIsMainCurrency().name().equals("YES")) {
            for (Currency allCurrency : findAllCurrencies()) {
                currencyRepository.updateCurrencyIsMain(allCurrency.getId());
            }

        }

        currencyRepository.editCurrency(code, name, exchangeRate, isMainCurrency, id, editUserId, dateEdit);
    }
}

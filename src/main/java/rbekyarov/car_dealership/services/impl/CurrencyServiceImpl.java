package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.CurrencyDTO;
import rbekyarov.car_dealership.models.entity.Currency;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.models.entity.enums.IsMainCurrency;
import rbekyarov.car_dealership.repository.CurrencyRepository;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.services.CurrencyService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static rbekyarov.car_dealership.services.CommonService.getUserEntity;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, ModelMapper modelMapper,
                               UserRepository userRepository) {
        this.currencyRepository = currencyRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public List<Currency> findAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public void addCurrency(CurrencyDTO currencyDTO) {
        Currency currency = modelMapper.map(currencyDTO, Currency.class);
        //get and set Author
//        UserEntity user = getUserEntity();
//        currency.setAuthor(user);
        currency.setAuthor(userRepository.getUsersById(1L));
        // set dateCreated
        currency.setDateCreate(LocalDate.now());

        if (currencyDTO.getIsMainCurrency().name().equals("YES")) {
            currency.setExchangeRate(1.00);
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
    public void editCurrency(CurrencyDTO currencyDTO, Long id) {
        String code = currencyDTO.getCode();
        String name = currencyDTO.getName();
        double exchangeRate = currencyDTO.getExchangeRate();
        IsMainCurrency isMainCurrency = currencyDTO.getIsMainCurrency();

//        UserEntity user = getUserEntity();
//        Long editUserId = user.getId();
        Long editUserId = 1L;
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        if (currencyDTO.getIsMainCurrency().name().equals("YES")) {
            for (Currency allCurrency : findAllCurrencies()) {
                currencyRepository.updateCurrencyIsMain(allCurrency.getId());
            }

        }

        currencyRepository.editCurrency(code, name, exchangeRate, isMainCurrency, id, editUserId, dateEdit);
    }

    @Override
    public Currency findMainCurrency() {
        List<Currency> mainCurrency = currencyRepository.findMainCurrency();
        Currency currency = new Currency();
        for (Currency currencyOnList : mainCurrency) {
            currency = currencyOnList;
        }
        return currency;
    }
}

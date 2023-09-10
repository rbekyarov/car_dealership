package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.CurrencyDTO;
import rbekyarov.car_dealership.models.entity.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<Currency> findAllCurrencies();

    void addCurrency(CurrencyDTO currencyDTO, HttpSession session);

    void removeCurrencyById(Long id);

    Optional<Currency> findById(Long id);

    void editCurrency(CurrencyDTO currencyDTO, Long id, HttpSession session);


    Currency  findMainCurrency();
}

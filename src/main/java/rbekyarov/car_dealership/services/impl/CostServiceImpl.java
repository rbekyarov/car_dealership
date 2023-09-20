package rbekyarov.car_dealership.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.CostDTO;
import rbekyarov.car_dealership.models.entity.Car;
import rbekyarov.car_dealership.models.entity.Cost;
import rbekyarov.car_dealership.models.entity.Currency;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.repository.CostRepository;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.repository.VendorRepository;
import rbekyarov.car_dealership.services.CarService;
import rbekyarov.car_dealership.services.CostService;
import rbekyarov.car_dealership.services.CurrencyService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static rbekyarov.car_dealership.services.CommonService.getUserEntity;

@Service
public class CostServiceImpl implements CostService {

    private final CostRepository costRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;
    private final CurrencyService currencyService;
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;

    public CostServiceImpl(CostRepository costRepository, ModelMapper modelMapper, CarService carService, CurrencyService currencyService, VendorRepository vendorRepository, UserRepository userRepository) {
        this.costRepository = costRepository;
        this.modelMapper = modelMapper;
        this.carService = carService;
        this.currencyService = currencyService;
        this.vendorRepository = vendorRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Cost> findAllCosts() {
        return costRepository.findAll();
    }

    @Override
    public void addCost(CostDTO costDTO) {
        Cost cost = modelMapper.map(costDTO, Cost.class);
        //checking the cost if it is for a car
        Car car = carService.findById(costDTO.getCarId()).get();

        if (costDTO.getCarId() != null) {
            //Check Currency

            BigDecimal amount = costDTO.getAmount();
            BigDecimal priceCosts = car.getPriceCosts();
            BigDecimal priceSaleMin = car.getPriceSaleMin();
            priceSaleMin = priceSaleMin.add(amount);
            BigDecimal priceSale = car.getPriceSale();
            priceSale = priceSale.add(amount);
            priceCosts = priceCosts.add(amount);
            carService.updatePricesAfterAddCost(priceCosts, priceSale, priceSaleMin, car.getId());
        }
        //SET CURRENCY
        Currency currency = currencyService.findMainCurrency();
        cost.setCurrency(currency);
        //SET VENDOR
        cost.setVendor(vendorRepository.findById(costDTO.getVendorId()).orElseThrow());


        UserEntity user = getUserEntity();
        cost.setAuthor(user);

        // set dateCreated
        cost.setDateCreate(LocalDate.now());
        cost.setCar(car);

        costRepository.save(cost);
    }


    @Override
    public Optional<Cost> findById(Long id) {
        return costRepository.findById(id);
    }

    @Override
    public void editCost(Long vendorId, Long carId, String description, String invoiceNo, BigDecimal amount, LocalDate dateCost, Long id) {

        //Checked car is changed
        Cost costForEdit = costRepository.findById(id).get();
        if (carId != null) {
            if (!Objects.equals(carId, costForEdit.getCar().getId())) {
                //Remove cost and deductible prices old car
                BigDecimal deductibleAmount = costForEdit.getAmount();
                Car currentCar = costForEdit.getCar();
                BigDecimal priceCosts = currentCar.getPriceCosts();
                BigDecimal priceSaleMin = currentCar.getPriceSaleMin();
                priceSaleMin = priceSaleMin.subtract(deductibleAmount);
                BigDecimal priceSale = currentCar.getPriceSale();
                priceSale = priceSale.subtract(deductibleAmount);
                priceCosts = priceCosts.subtract(deductibleAmount);
                carService.updatePricesAfterAddCost(priceCosts, priceSale, priceSaleMin, currentCar.getId());
                // Add cost and calculate prices new Car
                Car newCar = carService.findById(carId).get();
                BigDecimal priceCostsNewCar;
                BigDecimal priceSaleMinNewCar = newCar.getPriceSaleMin();
                priceSaleMinNewCar = priceSaleMinNewCar.add(amount);
                BigDecimal priceSaleNewCar = newCar.getPriceSale();
                priceSaleNewCar = priceSaleNewCar.add(amount);
                priceCostsNewCar = priceCosts.add(amount);
                carService.updatePricesAfterAddCost(priceCostsNewCar, priceSaleNewCar, priceSaleMinNewCar, carId);
            }
        }


        UserEntity user = getUserEntity();
        Long editUserId = user.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        costRepository.editCost(vendorId, carId, description, invoiceNo, amount, dateCost, id, editUserId, dateEdit);
    }


    @Override
    public void removeCostById(Long id) {
        costRepository.deleteById(id);
    }
}

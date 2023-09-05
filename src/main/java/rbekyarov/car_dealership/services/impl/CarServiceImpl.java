package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.CarDTO;
import rbekyarov.car_dealership.models.entity.*;
import rbekyarov.car_dealership.repository.CarRepository;
import rbekyarov.car_dealership.repository.PricingPercentDataRepository;
import rbekyarov.car_dealership.services.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ModelService modelService;
    private final VendorService vendorService;
    private final PricingPercentDataService pricingPercentDataService;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, UserService userService, ModelService modelService, VendorService vendorService,
                          PricingPercentDataService pricingPercentDataService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.modelService = modelService;
        this.vendorService = vendorService;
        this.pricingPercentDataService = pricingPercentDataService;
    }

    @Override
    public List<Car> findAllBrands() {
        return carRepository.findAll();
    }

    @Override
    public void addCar(CarDTO carDTO, HttpSession session) {
        Car car = modelMapper.map(carDTO, Car.class);
        //Generate Car Name
        String name = generateCarName(carDTO);
        car.setName(name);
        //Set Vendor
        car.setVendorPurchase(vendorService.findById(carDTO.getVendorId()).orElseThrow());
        //Set Model
        car.setModel(modelService.findById(carDTO.getModelId()).orElseThrow());
        //Set PRICES
            //Find Pricing percents
        PricingPercentData pricingPercentData = pricingPercentDataService.findActivePricingPercentData().orElseThrow();
            //Set PriceSale: pricePurchase + (PercentSaleCar * pricePurchase/100) + CostsPrices(0)
        BigDecimal priceSale = carDTO.getPricePurchase().add(carDTO.getPricePurchase().multiply(BigDecimal.valueOf(pricingPercentData.getPercentSaleCar() / 100.0)));
        car.setPriceSale(priceSale);
            //Set PriceSaleMin
        BigDecimal priceSaleMin = priceSale.add(priceSale.multiply(BigDecimal.valueOf(pricingPercentData.getPercentSaleCarMin() / 100.0)));
        car.setPriceSaleMin(priceSaleMin);

        //Set EmptyCost Set
        car.setCosts(new HashSet<>());
        //Set EmptyPicture Set
        car.setPictures(new HashSet<>());

        //get and set Author
        car.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        car.setDateCreate(LocalDate.now());
        carRepository.save(car);
    }


    @Override
    public void removeCarById(Long id) {

    }

    @Override
    public Optional<Car> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void editCar(CarDTO carDTO, Long id, HttpSession session) {

    }

    private String generateCarName(CarDTO carDTO) {
        Long modelId = carDTO.getModelId();
        Model model = modelService.findById(modelId).orElseThrow();
        Brand brand = model.getBrand();
        String modelName = model.getName();
        String brandName = brand.getName();
        String vinNumber = carDTO.getVinNumber();


        return brandName + " " + modelName + " " + vinNumber;
    }
}

package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.CostDTO;
import rbekyarov.car_dealership.models.entity.Car;
import rbekyarov.car_dealership.models.entity.Cost;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.repository.CostRepository;
import rbekyarov.car_dealership.repository.VendorRepository;
import rbekyarov.car_dealership.services.CarService;
import rbekyarov.car_dealership.services.CostService;
import rbekyarov.car_dealership.services.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CostServiceImpl implements CostService {

    private final CostRepository costRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CarService carService;
    private final VendorRepository vendorRepository;

    public CostServiceImpl(CostRepository costRepository, ModelMapper modelMapper, UserService userService,
                           CarService carService, VendorRepository vendorRepository) {
        this.costRepository = costRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.carService = carService;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<Cost> findAllCosts() {
        return costRepository.findAll();
    }

    @Override
    public void addCost(CostDTO costDTO, HttpSession session) {
        Cost cost = modelMapper.map(costDTO, Cost.class);
        //checking the cost if it is for a car
        Car car = carService.findById(costDTO.getCarId()).get();
        if(costDTO.getCarId() != null){
            BigDecimal priceCosts = car.getPriceCosts();
            BigDecimal priceSaleMin = car.getPriceSaleMin();
            priceSaleMin = priceSaleMin.add(costDTO.getAmount());
            BigDecimal priceSale = car.getPriceSale();
            priceSale = priceSale.add(costDTO.getAmount());
            priceCosts =priceCosts.add(costDTO.getAmount());
            carService.updatePricesAfterAddCost(priceCosts,priceSale,priceSaleMin,car.getId());
        }
        cost.setVendor(vendorRepository.findById(costDTO.getVendorId()).orElseThrow());
        //cost.setAuthor(userService.getAuthorFromSession(session));
        cost.setAuthor(userService.findById(1L).get());
        // set dateCreated
        cost.setDateCreate(LocalDate.now());
        cost.setCar(car);
        costRepository.save(cost);
    }

    @Override
    public void removeCostById(Long id) {
        costRepository.deleteById(id);
    }

    @Override
    public Optional<Cost> findById(Long id) {
        return costRepository.findById(id);
    }

    @Override
    public void editCost(Long vendorId, String description, String invoiceNo, BigDecimal amount, LocalDate dateCost, Long id, HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        costRepository.editCost(vendorId,description,invoiceNo,amount,dateCost, id,editAuthorId, dateEdit);
    }
}

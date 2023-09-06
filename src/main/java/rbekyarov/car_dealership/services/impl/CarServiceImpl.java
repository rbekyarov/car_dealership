package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.CarDTO;
import rbekyarov.car_dealership.models.dto.PictureDTO;
import rbekyarov.car_dealership.models.entity.*;
import rbekyarov.car_dealership.repository.CarRepository;
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
    private final PictureService pictureService;
    private final PricingPercentDataService pricingPercentDataService;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, UserService userService, ModelService modelService, VendorService vendorService,
                          PictureService pictureService, PricingPercentDataService pricingPercentDataService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.modelService = modelService;
        this.vendorService = vendorService;
        this.pictureService = pictureService;
        this.pricingPercentDataService = pricingPercentDataService;
    }

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void addCar(CarDTO carDTO,Set<Picture>pictures, HttpSession session) {
        Car car = modelMapper.map(carDTO, Car.class);
        //Check and get Next CarID in car table
        List<Car> allCars = carRepository.findAll();
       Long nextCarId=  (allCars.size())+1L;

       //Check and get Next PictureID in picture table
        Set<Picture> pictureSetChanged = new HashSet<>();
        List<Picture> allPictures = pictureService.findAllPictures();

        Long generator = (long) (allPictures.size());
        for (Picture picture : pictures) {
            generator++;
            picture.setId(generator);
            pictureSetChanged.add(picture);
        }

        //Add Pictures in database
        addPictureInCarAndAddPictureInRepo(pictures, session);

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
            //Set PriceSaleMin:
        BigDecimal priceSaleMin = carDTO.getPricePurchase().add(carDTO.getPricePurchase().multiply(BigDecimal.valueOf(pricingPercentData.getPercentSaleCarMin() / 100.0)));
        car.setPriceSaleMin(priceSaleMin);


        //get and set Author
        car.setAuthor(userService.getAuthorFromSession(session));
        //for testing -> car.setAuthor(userService.findById(1L).get());
        // set dateCreated
        car.setDateCreate(LocalDate.now());
        //Add car in database
        carRepository.save(car);

        //Update Pictures table fields car_id
        pictureService.updatePicturesTableFieldsCarId(pictureSetChanged, nextCarId);
    }




    @Override
    public void removeCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public BigDecimal calculatePriceOnCars(Set<Long> carsIds) {
        BigDecimal sum = new BigDecimal("0");
        for (Long id : carsIds) {
            sum.add(carRepository.calculatePriceOnCars(id));
        }

       return sum;
    }

    @Override
    public Set<Car> addCarInOfferAndSale(Set<Long> carIds) {
        Set<Car>carSet = new HashSet<>();
        for (Long carId : carIds) {
            if (carRepository.findById(carId).isPresent()){
                carSet.add(carRepository.findById(carId).orElseThrow());
            }
        }

        return carSet;
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
    private void addPictureInCarAndAddPictureInRepo(Set<Picture> pictures, HttpSession session) {
        if(!pictures.isEmpty()){
            for (Picture picture : pictures) {

                PictureDTO pictureDTO = new PictureDTO();
                pictureDTO.setName(picture.getName());
                pictureService.addPicture(pictureDTO, session);
            }
        }
    }
}

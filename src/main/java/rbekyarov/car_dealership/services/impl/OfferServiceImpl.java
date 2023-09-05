package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.OfferDTO;
import rbekyarov.car_dealership.models.entity.Car;
import rbekyarov.car_dealership.models.entity.Offer;
import rbekyarov.car_dealership.repository.OfferRepository;
import rbekyarov.car_dealership.services.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CarService carService;
    private final ClientService clientService;
    private final SellerService sellerService;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, UserService userService, CarService carService, ClientService clientService, SellerService sellerService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.carService = carService;
        this.clientService = clientService;
        this.sellerService = sellerService;
    }

    @Override
    public List<Offer> findAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public void addOffer(OfferDTO offerDTO, HttpSession session) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        Set<Long> carIds = offerDTO.getCarIds();

        //ADD Cars
        Set<Car> cars = carService.addCarInOfferAndSale(carIds);
        offer.setCars(cars);
        //SET PRICE OFFER
        BigDecimal price = carService.calculatePriceOnCars(carIds);
        offer.setPrice(price);
        //SET TOTAL PRICE OFFER
        BigDecimal discountPercent = offerDTO.getDiscount();
        BigDecimal percent = new BigDecimal("100");
        BigDecimal discount = discountPercent.divide(percent);
        offer.setTotalPrice(price.subtract(discount));

        //get and set Client
        offer.setClient(clientService.findById(offerDTO.getClientId()).orElseThrow());
        //get and set Seller
        offer.setSeller(sellerService.findById(offerDTO.getSellerId()).orElseThrow());
        //get and set Author
        offer.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        offer.setDateCreate(LocalDate.now());
        offerRepository.save(offer);

    }

    @Override
    public void removeOfferById(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public Optional<Offer> findById(Long id) {
        return offerRepository.findById(id);
    }

    @Override
    public void editOffer(OfferDTO offerDTO, Long id, HttpSession session) {

    }
}

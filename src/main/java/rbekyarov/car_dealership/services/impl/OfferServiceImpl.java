package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.OfferDTO;
import rbekyarov.car_dealership.models.entity.Car;
import rbekyarov.car_dealership.models.entity.Offer;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.models.entity.enums.StatusOffer;
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
        Offer offer = new Offer();
        Set<Long> carIds = offerDTO.getCarIds();

        //SET PRICE OFFER
        BigDecimal price = carService.calculatePriceOnCars(carIds);
        offer.setPrice(price);

        //CALCULATE AND SET TOTAL PRICE AND DISCOUNT OFFER
        calculateAndSetTotalPriceAndDiscount(offerDTO, offer, price);

        // SET STATUS OFFER - for new offer  - always will be 'proposed'
        offer.setStatusOffer(StatusOffer.proposed);

        //get and set Client
        offer.setClient(clientService.findById(offerDTO.getClientId()).orElseThrow());

        //get and set Seller
        offer.setSeller(sellerService.findById(offerDTO.getSellerId()).orElseThrow());

        //get and set Author
        //offer.setAuthor(userService.getAuthorFromSession(session));
        offer.setAuthor(userService.findById(1L).get());

        // set dateCreated
        offer.setDateCreate(LocalDate.now());

        offerRepository.save(offer);

        //Change in the car_table fields offer_id
        //find Cars
        Set<Car> cars = carService.addCarInOfferAndSale(carIds);
        Long offerId = offerRepository.findAll().size() + 0L;

        carService.updateCarOfferIdFields(cars, offerId);
        //offer.setCars(cars);

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

        Offer offer = offerRepository.findById(id).get();
        Set<Long> carIds = offerDTO.getCarIds();
        //Clear cars in the offer

        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        //offerRepository.editOffer(name, id,editAuthorId, dateEdit);
    }

    private static void calculateAndSetTotalPriceAndDiscount(OfferDTO offerDTO, Offer offer, BigDecimal price) {
        BigDecimal discountPercent = new BigDecimal(0);

        if (offerDTO.getDiscount() == null) {
            discountPercent = new BigDecimal(0);
        }
        BigDecimal percent = new BigDecimal("100");
        BigDecimal discountP = discountPercent.divide(percent);
        BigDecimal discount = price.multiply(discountP);
        BigDecimal totalPrice = price.subtract(discount);
        offer.setDiscount(discount);
        offer.setTotalPrice(totalPrice);
    }
}

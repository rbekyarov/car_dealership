package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.dto.OfferDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Offer;
import rbekyarov.car_dealership.models.entity.enums.StatusOffer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OfferService {
    List<Offer> findAllOffers();

    void addOffer(OfferDTO offerDTO, HttpSession session);

    void removeOfferById(Long id);

    Optional<Offer> findById(Long id);

    void editOffer(OfferDTO offerDTO, Long id, HttpSession session);

}

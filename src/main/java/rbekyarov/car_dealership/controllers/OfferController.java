package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.dto.CarDTO;
import rbekyarov.car_dealership.models.dto.OfferDTO;
import rbekyarov.car_dealership.models.entity.Car;
import rbekyarov.car_dealership.models.entity.Offer;
import rbekyarov.car_dealership.models.entity.Picture;
import rbekyarov.car_dealership.services.CarService;
import rbekyarov.car_dealership.services.OfferService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping
    public ResponseEntity<?> getAllOffers() {
        List<Offer> allOffers = offerService.findAllOffers();
        return new ResponseEntity<>(allOffers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOfferById(@PathVariable Long id) {
        Offer offer = offerService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offer not exist with id: " + id));
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createOffer(@RequestBody @Valid OfferDTO offerDTO, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            offerService.addOffer(offerDTO, session);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOffer(@PathVariable Long id, @RequestBody @Valid OfferDTO offerDTO, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {

            offerService.editOffer(offerDTO,id, session);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOffer(@PathVariable Long id) {
        offerService.removeOfferById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

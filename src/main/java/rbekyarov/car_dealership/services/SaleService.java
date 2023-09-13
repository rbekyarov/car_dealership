package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.SaleDTO;
import rbekyarov.car_dealership.models.entity.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleService {
    List<Sale> findAllSales();

    void addSale(SaleDTO saleDTO);

    void removeSaleById(Long id);

    Optional<Sale> findById(Long id);

    void editSale(SaleDTO saleDTO, Long id);

    void updateStatusInvoicedToYes(Long saleId);
    void transformOfferToSale(Long offerId );
}

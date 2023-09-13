package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.SellerDTO;
import rbekyarov.car_dealership.models.entity.Seller;
import rbekyarov.car_dealership.models.entity.enums.Position;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SellerService {
    List<Seller> findAllSellers();

    void addSeller(SellerDTO sellerDTO);

    void removeSellerById(Long id);

    Optional<Seller> findById(Long id);

    void editSeller(String firstName,String lastName,Position position,BigDecimal salary, Long id);

    void addCommission(BigDecimal totalProfit, Long sellerId);
}

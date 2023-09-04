package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.dto.VendorDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorService {
    List<Vendor> findAllVendors();

    void addVendor(VendorDTO vendorDTO, HttpSession session);

    void removeVendorById(Long id);

    Optional<Vendor> findById(Long id);

    void editVendor(String name,String country,String city,String address,String vatNumber,String email, Long id, HttpSession session);
}

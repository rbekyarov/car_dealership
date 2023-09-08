package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.services.BrandService;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<Brand> getAllClients() {
        return brandService.findAllBrands();
    }

    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable Long id) {
        return brandService.findById(id).orElseThrow();
    }

    @PostMapping
    void createBrand(@RequestBody BrandDTO brandDTO, HttpSession session) {
        brandService.addBrand(brandDTO,session);
    }

    @PutMapping("/{id}")
    void updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO, HttpSession session) {
         brandService.editBrand(brandDTO.getName(), id,session);

    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandService.removeBrandById(id);
    }
}

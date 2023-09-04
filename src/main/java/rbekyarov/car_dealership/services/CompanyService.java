package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.dto.CompanyDTO;
import rbekyarov.car_dealership.models.entity.BankAccount;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> findAllCompanies();

    void addCompany(CompanyDTO companyDTO, HttpSession session);

    void removeCompanyById(Long id);

    Optional<Company> findById(Long id);

    void editCompany(String name,Long pictureId,String country,String city,String address,String vatNumber,String email,String managerName, Long id, HttpSession session);

}


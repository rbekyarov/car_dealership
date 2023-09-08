package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.CompanyDTO;
import rbekyarov.car_dealership.models.entity.Company;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.repository.CompanyRepository;
import rbekyarov.car_dealership.repository.PictureRepository;
import rbekyarov.car_dealership.services.CompanyService;
import rbekyarov.car_dealership.services.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final PictureRepository pictureRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, UserService userService, PictureRepository pictureRepository) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void addCompany(CompanyDTO companyDTO, HttpSession session) {
        Company company = modelMapper.map(companyDTO, Company.class);
        company.setLogoName(pictureRepository.findById(companyDTO.getPictureId()).orElseThrow());
        //get and set Author
        company.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        company.setDateCreate(LocalDate.now());
        companyRepository.save(company);
    }

    @Override
    public void removeCompanyById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public void editCompany(String name, Long pictureId, String country, String city, String address, String vatNumber, String email, String managerName, Long id, HttpSession session) {
        User editUser = userService.getAuthorFromSession(session);
        Long editUserId = editUser.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        companyRepository.editCompany(name,pictureId,country,city,address,vatNumber,email,managerName, id,editUserId, dateEdit);
    }


    }



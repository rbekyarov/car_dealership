package rbekyarov.car_dealership.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.CompanyDTO;
import rbekyarov.car_dealership.models.entity.Company;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.repository.CompanyRepository;
import rbekyarov.car_dealership.repository.PictureRepository;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.services.CompanyService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static rbekyarov.car_dealership.services.CommonService.getUserEntity;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    private final PictureRepository pictureRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, UserRepository userRepository, PictureRepository pictureRepository) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
    }


    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void addCompany(CompanyDTO companyDTO) {
        Company company = modelMapper.map(companyDTO, Company.class);
        //company.setLogoName(pictureRepository.findById(companyDTO.getPictureId()).orElseThrow());
        //get and set Author

//        UserEntity user = getUserEntity();
//        company.setAuthor(user);
        company.setAuthor(userRepository.getUsersById(1L));

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
    public void editCompany(String name, Long pictureId, String country, String city, String address, String vatNumber, String email, String managerName, Long id) {
//        UserEntity user = getUserEntity();
//        Long editUserId = user.getId();
        Long editUserId = 1L;

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        companyRepository.editCompany(name,pictureId,country,city,address,vatNumber,email,managerName, id,editUserId, dateEdit);
    }


    }



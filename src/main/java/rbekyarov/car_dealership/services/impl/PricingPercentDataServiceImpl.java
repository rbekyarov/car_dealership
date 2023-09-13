package rbekyarov.car_dealership.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.PricingPercentDataDTO;
import rbekyarov.car_dealership.models.entity.PricingPercentData;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.models.entity.enums.ActivePricingPercentData;
import rbekyarov.car_dealership.repository.PricingPercentDataRepository;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.services.PricingPercentDataService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static rbekyarov.car_dealership.services.CommonService.getUserEntity;

@Service
public class PricingPercentDataServiceImpl implements PricingPercentDataService {

    private final PricingPercentDataRepository pricingPercentDataRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public PricingPercentDataServiceImpl(PricingPercentDataRepository pricingPercentDataRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.pricingPercentDataRepository = pricingPercentDataRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public List<PricingPercentData> findAllPricingPercentDates() {
        return pricingPercentDataRepository.findAll();
    }

    @Override
    public void addPricingPercentData(PricingPercentDataDTO pricingPercentDataDTO) {
        PricingPercentData pricingPercentData = modelMapper.map(pricingPercentDataDTO, PricingPercentData.class);

        //Set NEW record ActivePricingPercentData.YES
        pricingPercentData.setActivePricingPercentData(ActivePricingPercentData.YES);

        //Set other record ActivePricingPercentData.NO
        pricingPercentDataRepository.setAllActivePricingPercentDataToNO();

        //get and set Author
//        UserEntity user = getUserEntity();
//        pricingPercentData.setAuthor(user);
        pricingPercentData.setAuthor(userRepository.getUsersById(1L));

        // set dateCreated
        pricingPercentData.setDateCreate(LocalDate.now());
        pricingPercentDataRepository.save(pricingPercentData);
    }



    @Override
    public void removePricingPercentDataById(Long id) {
        pricingPercentDataRepository.deleteById(id);
    }

    @Override
    public Optional<PricingPercentData> findById(Long id) {
        return pricingPercentDataRepository.findById(id);
    }

    @Override
    public Optional<PricingPercentData> findActivePricingPercentData() {
        return pricingPercentDataRepository.findActivePricingPercentData();
    }

    @Override
    public void editPricingPercentData(int percentSaleCar, int percentSaleCarMin, int percentCommission, ActivePricingPercentData activePricingPercentData,int percentVAT, Long id) {
        if(activePricingPercentData.name().equals("YES")){
            //Set other record ActivePricingPercentData.NO
            pricingPercentDataRepository.setAllActivePricingPercentDataToNO();
        }

//        UserEntity user = getUserEntity();
//        Long editUserId = user.getId();

        Long editUserId = 1L;

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        pricingPercentDataRepository.editPricingPercentData(percentSaleCar,percentSaleCarMin,percentCommission,activePricingPercentData,percentVAT, id,editUserId, dateEdit);
    }


}

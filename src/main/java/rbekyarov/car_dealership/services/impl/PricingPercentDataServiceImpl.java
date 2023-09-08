package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.PricingPercentDataDTO;
import rbekyarov.car_dealership.models.entity.PricingPercentData;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.models.entity.enums.ActivePricingPercentData;
import rbekyarov.car_dealership.repository.PricingPercentDataRepository;
import rbekyarov.car_dealership.services.PricingPercentDataService;
import rbekyarov.car_dealership.services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class PricingPercentDataServiceImpl implements PricingPercentDataService {

    private final PricingPercentDataRepository pricingPercentDataRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public PricingPercentDataServiceImpl(PricingPercentDataRepository pricingPercentDataRepository, ModelMapper modelMapper, UserService userService) {
        this.pricingPercentDataRepository = pricingPercentDataRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<PricingPercentData> findAllPricingPercentDates() {
        return pricingPercentDataRepository.findAll();
    }

    @Override
    public void addPricingPercentData(PricingPercentDataDTO pricingPercentDataDTO, HttpSession session) {
        PricingPercentData pricingPercentData = modelMapper.map(pricingPercentDataDTO, PricingPercentData.class);

        //Set NEW record ActivePricingPercentData.YES
        pricingPercentData.setActivePricingPercentData(ActivePricingPercentData.YES);

        //Set other record ActivePricingPercentData.NO
        pricingPercentDataRepository.setAllActivePricingPercentDataToNO();

        //get and set Author
        pricingPercentData.setAuthor(userService.getAuthorFromSession(session));
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
    public void editPricingPercentData(int percentSaleCar, int percentSaleCarMin, int percentCommission, ActivePricingPercentData activePricingPercentData,int percentVAT, Long id, HttpSession session) {
        if(activePricingPercentData.name().equals("YES")){
            //Set other record ActivePricingPercentData.NO
            pricingPercentDataRepository.setAllActivePricingPercentDataToNO();
        }

        User editUser = userService.getAuthorFromSession(session);
        Long editUserId = editUser.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        pricingPercentDataRepository.editPricingPercentData(percentSaleCar,percentSaleCarMin,percentCommission,activePricingPercentData,percentVAT, id,editUserId, dateEdit);
    }


}

package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.CostDTO;
import rbekyarov.car_dealership.models.entity.Cost;
import rbekyarov.car_dealership.models.entity.Model;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.repository.CostRepository;
import rbekyarov.car_dealership.repository.VendorRepository;
import rbekyarov.car_dealership.services.CostService;
import rbekyarov.car_dealership.services.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CostServiceImpl implements CostService {

    private final CostRepository costRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final VendorRepository vendorRepository;

    public CostServiceImpl(CostRepository costRepository, ModelMapper modelMapper, UserService userService,
                           VendorRepository vendorRepository) {
        this.costRepository = costRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<Cost> findAllCosts() {
        return costRepository.findAll();
    }

    @Override
    public void addCost(CostDTO costDTO, HttpSession session) {
        Cost cost = modelMapper.map(costDTO, Cost.class);
        cost.setVendor(vendorRepository.findById(costDTO.getVendorId()).orElseThrow());
        cost.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        cost.setDateCreate(LocalDate.now());
        costRepository.save(cost);
    }

    @Override
    public void removeCostById(Long id) {
        costRepository.deleteById(id);
    }

    @Override
    public Optional<Cost> findById(Long id) {
        return costRepository.findById(id);
    }

    @Override
    public void editCost(Long vendorId, String description, String invoiceNo, BigDecimal amount, LocalDate dateCost, Long id, HttpSession session) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        costRepository.editCost(vendorId,description,invoiceNo,amount,dateCost, id,editAuthorId, dateEdit);
    }
}

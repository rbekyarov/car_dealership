package rbekyarov.car_dealership.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.SellerDTO;
import rbekyarov.car_dealership.models.entity.Seller;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.models.entity.enums.Position;
import rbekyarov.car_dealership.repository.SellerRepository;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.services.SellerService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static rbekyarov.car_dealership.services.CommonService.getUserEntity;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public List<Seller> findAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public void addSeller(SellerDTO sellerDTO) {
        Seller seller = modelMapper.map(sellerDTO, Seller.class);
        seller.setTotalProfit(new BigDecimal(0));
        //get and set Author
        UserEntity user = getUserEntity();
       seller.setAuthor(user);

        // set dateCreated
        seller.setDateCreate(LocalDate.now());
        sellerRepository.save(seller);
    }

    @Override
    public void removeSellerById(Long id) {
        sellerRepository.deleteById(id);
    }

    @Override
    public Optional<Seller> findById(Long id) {
        return sellerRepository.findById(id);
    }

    @Override
    public void editSeller(String firstName, String lastName, Position position, BigDecimal salary, Long id) {
       UserEntity user = getUserEntity();
        Long editUserId = user.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        sellerRepository.editSeller(firstName,lastName,position,salary, id,editUserId, dateEdit);
    }

    @Override
    public void addCommission(BigDecimal totalProfit, Long sellerId) {
        sellerRepository.addCommission(totalProfit,sellerId);
    }
}

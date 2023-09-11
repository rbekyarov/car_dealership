package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.SellerDTO;
import rbekyarov.car_dealership.models.entity.Seller;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.models.entity.enums.Position;
import rbekyarov.car_dealership.repository.SellerRepository;
import rbekyarov.car_dealership.services.SellerService;
import rbekyarov.car_dealership.services.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, UserService userService) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<Seller> findAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public void addSeller(SellerDTO sellerDTO, HttpSession session) {
        Seller seller = modelMapper.map(sellerDTO, Seller.class);
        seller.setTotalProfit(new BigDecimal(0));
        //get and set Author
       // seller.setAuthor(userService.getAuthorFromSession(session));
        seller.setAuthor(userService.findById(1L).get());
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
    public void editSeller(String firstName, String lastName, Position position, BigDecimal salary, Long id, HttpSession session) {
        User editUser = userService.getAuthorFromSession(session);
        Long editUserId = editUser.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        sellerRepository.editSeller(firstName,lastName,position,salary, id,editUserId, dateEdit);
    }

    @Override
    public void addCommission(BigDecimal totalProfit, Long sellerId) {
        sellerRepository.addCommission(totalProfit,sellerId);
    }
}

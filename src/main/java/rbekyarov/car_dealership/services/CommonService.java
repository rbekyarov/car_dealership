package rbekyarov.car_dealership.services;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.AppUserDetails;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.security.services.UserDetailsImpl;
import rbekyarov.car_dealership.security.services.UserDetailsServiceImpl;


@Service
public class CommonService {
  private static UserRepository userRepository;


    public CommonService(UserRepository userRepository) {
        CommonService.userRepository = userRepository;
    }

    public static UserEntity getUserEntity() {
        Long userId = getUserId();

        return userRepository.getUsersById(userId);
    }

    public static Long getUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return  userDetails.getId();
    }



}

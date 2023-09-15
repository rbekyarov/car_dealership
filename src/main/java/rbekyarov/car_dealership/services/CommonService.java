package rbekyarov.car_dealership.services;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.AppUserDetails;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;


@Service
public class CommonService {
    private  static UserDetailsService customUserDetailsService;
  private static UserRepository userRepository;
    public static boolean inCart = false;

    public CommonService() {
    }

    public static UserEntity getUserEntity() {
        Long userId = getUserId();

        return userRepository.getUsersById(userId);
    }

    public static Long getUserId() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

        return  userDetails.getId();
    }



}

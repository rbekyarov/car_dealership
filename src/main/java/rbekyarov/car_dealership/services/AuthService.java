package rbekyarov.car_dealership.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.UserDTO;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.repository.RoleRepository;
import rbekyarov.car_dealership.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserDetailsService detailsService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    private final  AuthenticationManager customAuthenticationManager;


    @Autowired
    public AuthService(UserRepository userRepository, UserDetailsService detailsService, PasswordEncoder passwordEncoder, RoleRepository roleRepository,  AuthenticationManager customAuthenticationManager) {
        this.userRepository = userRepository;
        this.detailsService = detailsService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;

        this.customAuthenticationManager = customAuthenticationManager;
    }

    public List<UserEntity> findAllUserById() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public void removeUserById(Long id) {
    }

    public void addUser(UserDTO userDTO) {
        if (userDTO.getPassword().equals(userDTO.getRepeatPassword())) {

            Optional<UserEntity> email = this.userRepository.findByEmail(userDTO.getEmail());
            Optional<UserEntity> username = this.userRepository.findByUsername(userDTO.getUsername());

            if (!email.isPresent() || !username.isPresent()) {
                UserEntity user = new UserEntity();

                user.setUsername(userDTO.getUsername());
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                user.addRole(roleRepository.getReferenceById(userDTO.getRoleId()));
                user.setPosition(userDTO.getPosition());

                this.userRepository.save(user);
            }
        }

    }

//    public void updateUserDetails(Long id, String username, String password) {
//
//        this.userRepository.editUsername(id, username);
//        this.userRepository.editUserPassword(id, password);
//
//        UserEntity currentUser = this.userRepository.findById(id).get();
//        SecurityContextHolder.getContext()
//                .setAuthentication(this.getAuthenticationToken(currentUser.getEmail()));
//    }
//
//    private Authentication getAuthenticationToken(String email) {
//        UserDetails userDetails = detailsService.loadUserByUsername(email);
//
//        return new UsernamePasswordAuthenticationToken(
//                userDetails,
//                userDetails.getPassword(),
//                userDetails.getAuthorities()
//        );
//    }



}

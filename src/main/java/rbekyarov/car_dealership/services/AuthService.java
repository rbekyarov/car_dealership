package rbekyarov.car_dealership.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.RegisterUserDTO;
import rbekyarov.car_dealership.models.dto.UserDTO;
import rbekyarov.car_dealership.models.entity.Role;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.repository.RoleRepository;
import rbekyarov.car_dealership.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserDetailsService detailsService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Autowired
    public AuthService(UserRepository userRepository, UserDetailsService detailsService, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.detailsService = detailsService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public boolean login(Authentication authenticationFromFrontEnd){

        SecurityContextHolder.setContext(new SecurityContext() {
            @Override
            public Authentication getAuthentication() {
                return authenticationFromFrontEnd;
            }

            @Override
            public void setAuthentication(Authentication authentication) {
                authentication = authenticationFromFrontEnd;

            }
        });
        //authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authenticationFromFrontEnd == null || authenticationFromFrontEnd instanceof AnonymousAuthenticationToken) {

        }

        return false;
    }

    public boolean register(RegisterUserDTO registerUserDTO) {
        if (!registerUserDTO.getPassword().equals(registerUserDTO.getRepeatPassword())) {
            return false;
        }

        Optional<UserEntity> email = this.userRepository.findByEmail(registerUserDTO.getEmail());
        Optional<UserEntity> username = this.userRepository.findByUsername(registerUserDTO.getUsername());
        Role userRole = this.roleRepository.findByName("USER");
        if (email.isPresent() || username.isPresent()) {
            return false;
        }

        UserEntity user = new UserEntity();

        user.setUsername(registerUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setFirstName(registerUserDTO.getFirstName());
        user.setLastName(registerUserDTO.getLastName());
        user.setEmail(registerUserDTO.getEmail());
        user.addRole(userRole);
        this.userRepository.save(user);

        return true;
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

    public void updateUserDetails(Long id, String username, String password) {

        this.userRepository.editUsername(id, username);
        this.userRepository.editUserPassword(id, password);

        UserEntity currentUser = this.userRepository.findById(id).get();
        SecurityContextHolder.getContext()
                .setAuthentication(this.getAuthenticationToken(currentUser.getEmail()));
    }

    private Authentication getAuthenticationToken(String email) {
        UserDetails userDetails = detailsService.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
    }

}

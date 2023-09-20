package rbekyarov.car_dealership.services;


import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.ERole;
import rbekyarov.car_dealership.models.entity.Role;
import rbekyarov.car_dealership.models.entity.RoleEnum;
import rbekyarov.car_dealership.models.entity.UserEntity;

import rbekyarov.car_dealership.repository.RoleRepository;
import rbekyarov.car_dealership.repository.UserRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserInitService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserInitService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void initUsers() {
        if (userRepository.count() == 0) {
            initRoles();
            initAdmin();
            initModerator();
            initUser();
        }
    }

    private void initRoles() {
        if (this.roleRepository.count() == 0) {
            Arrays.stream(ERole.values())
                    .forEach(value -> {
                        this.roleRepository.save(new Role(value));
                    });
        }
    }


    void initAdmin() {

//        Role adminRole = this.roleRepository.findByName("ADMIN");
//
//        var admin = new UserEntity();
//        if (checkExistence("admin@com.com", "admin")) return;
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("123"));
//        admin.setFirstName("Admin");
//        admin.setLastName("Adminov");
//        admin.setEmail("admin@com.com");
//        admin.addRole(adminRole);
//
//        this.userRepository.save(admin);

    }

    void initModerator() {

//        Role moderatorRole = this.roleRepository.findByName("MODERATOR");
//
//        var moderator = new UserEntity();
//        if (checkExistence("moderator@com.com", "moderator")) return;
//        moderator.setUsername("moderator");
//        moderator.setPassword(passwordEncoder.encode("123"));
//        moderator.setFirstName("Moderator");
//        moderator.setLastName("Moderatorov");
//        moderator.setEmail("moderator@com.com");
//        moderator.addRole(moderatorRole);
//
//        this.userRepository.save(moderator);
    }

    void initUser() {

//        Role userRole = this.roleRepository.findByName("USER");
//
//        var user = new UserEntity();
//        if (checkExistence("user@com.com", "user")) return;
//        user.setUsername("user");
//        user.setPassword(passwordEncoder.encode("123"));
//        user.setFirstName("Petar");
//        user.setLastName("Petrov");
//        user.setEmail("user@com.com");
//        user.addRole(userRole);
//
//        this.userRepository.save(user);

    }

    boolean checkExistence(String email, String username) {
        Optional<UserEntity> entityEmail = this.userRepository.findByEmail(email);
        Optional<UserEntity> entityUsername = this.userRepository.findByUsername(username);
        if (entityEmail.isPresent() || entityUsername.isPresent()) {

            System.out.println("This user exists");
            return true;
        }
        return false;
    }


}

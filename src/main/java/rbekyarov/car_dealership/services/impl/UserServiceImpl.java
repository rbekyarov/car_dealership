package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.UserDTO;
import rbekyarov.car_dealership.models.dto.UserRegisterDTO;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.models.entity.enums.Position;
import rbekyarov.car_dealership.models.entity.enums.Role;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.services.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        User user = this.modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.saveAndFlush(user);

    }

    @Override
    public User loginUser(UserDTO userDTO) {

        Optional<User> byUsername = userRepository.findByUsername(userDTO.getUsername());
        User user = byUsername.get();
        return user;

    }
    @Override
    public boolean authenticate(String username, String password) {
        if(username.isEmpty() ||password.isEmpty()){
            return false;
        }
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return false;
        } else {
            return passwordEncoder.matches(password, userOptional.get().getPassword());
        }
    }

    @Override
    public List<User> findAllUserById() {
        return userRepository.findAllUserById();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void editUser(String email, Role role, Position  position,  Long id) {
        userRepository.editUser(email,role,position, id);
    }

    @Override
    public void editUserPassword(UserDTO userDTO, Long id) {
        String username = userDTO.getUsername();
        String encode = passwordEncoder.encode(userDTO.getPassword());
        userRepository.editUserPassword(encode, id , username);
    }



    @Override
    public void addUser(UserDTO userDTO) {

        User user = this.modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.saveAndFlush(user);

    }



    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getAuthorFromSession(HttpSession session) {
        Object userName = session.getAttribute("username");
        Optional<User> userOptional = userRepository.findByUsername(userName.toString());
        User user = userOptional.get();
        return user;
    }

}

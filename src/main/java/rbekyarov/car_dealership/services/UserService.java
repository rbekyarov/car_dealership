package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.UserDTO;
import rbekyarov.car_dealership.models.dto.UserRegisterDTO;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.models.entity.enums.Position;
import rbekyarov.car_dealership.models.entity.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerUser(UserRegisterDTO userRegisterDTO);

    boolean authenticate(String username, String password);

    User loginUser(UserDTO userDTO);

    List<User> findAllUserById();

    Optional<User> findById(Long id);

    void editUser(String email,Role role, Position position, Long id);

    void editUserPassword(String password, Long id);

    void addUser(UserDTO userDTO);

    void removeUserById(Long id);

    Optional<User> findByUsername(String username);

    User getAuthorFromSession(HttpSession session);
}

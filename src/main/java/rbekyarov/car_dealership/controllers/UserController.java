package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.dto.RegisterUserDTO;
import rbekyarov.car_dealership.models.dto.UserDTO;
import rbekyarov.car_dealership.models.dto.UserRegisterDTO;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.models.entity.enums.Position;
import rbekyarov.car_dealership.models.entity.enums.Role;
import rbekyarov.car_dealership.services.AuthService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> allUsers = authService.findAllUserById();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserEntity user = authService.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//
//            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
//        } else {
//            authService.addUser(userDTO);
//
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
//
//    }
//
//    @PostMapping
//    public ResponseEntity<?> registerUser(@RequestBody @Valid
//                                          RegisterUserDTO registerUserDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//
//            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
//        } else {
//            authService.register(registerUserDTO);
//
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
//
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO, HttpSession session, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//
//            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
//        } else {
//            String email = userDTO.getEmail();
//            Role role = userDTO.getRole();
//            Position position = userDTO.getPosition();
//            authService.editUser(email,role,position ,id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
   // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        authService.removeUserById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

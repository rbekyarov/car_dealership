package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.config.jwt.JwtTokenProvider;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.CurrentUserEntity;
import rbekyarov.car_dealership.models.dto.LoginDTO;
import rbekyarov.car_dealership.models.dto.RegisterUserDTO;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.services.AuthService;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;
import static rbekyarov.car_dealership.services.CommonService.getUserEntity;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    AuthenticationManager customAuthenticationManager;
    @Autowired
    private AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid
                                          RegisterUserDTO registerUserDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            authService.register(registerUserDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {

        try {
            String username = loginDTO.getUsername();
            Authentication authentication = customAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, loginDTO.getPassword()));
            String token = jwtTokenProvider.createToken(authentication);
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ok(model);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

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


    @SuppressWarnings("rawtypes")
    @GetMapping("/my-info")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(toList())
        );
        return ok(model);
    }


//        @PostMapping("/login")
//        public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO, BindingResult bindingResult) throws Exception {
//            if (bindingResult.hasErrors()) {
//                // Обработка грешки валидация
//                return new ResponseEntity<>("Validation failed", HttpStatus.BAD_REQUEST);
//            }
//
//            try {
//
//                Authentication authentication = customAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//
//
//            } catch (BadCredentialsException ex) {
//
//                throw new Exception("Invalid credentials");
//            } catch (AuthenticationException ex) {
//                // Други грешки при аутентикация
//                throw new Exception("Authentication failed");
//            }
//
//            // Успешен вход
//            UserEntity user = getUserEntity();
//            System.out.println(user.getId());
//            return new ResponseEntity<>(user.getId(), HttpStatus.OK);
//        }



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

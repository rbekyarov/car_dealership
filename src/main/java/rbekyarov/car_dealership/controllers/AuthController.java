package rbekyarov.car_dealership.controllers;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import rbekyarov.car_dealership.models.ERole;
import rbekyarov.car_dealership.models.entity.Role;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.payload.request.LoginRequest;
import rbekyarov.car_dealership.payload.request.SignupRequest;
import rbekyarov.car_dealership.payload.response.UserInfoResponse;
import rbekyarov.car_dealership.payload.response.MessageResponse;
import rbekyarov.car_dealership.repository.RoleRepository;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.security.jwt.JwtUtils;
import rbekyarov.car_dealership.security.services.UserDetailsImpl;

//for Angular Client (withCredentials)
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("token", jwtCookie.getValue());
    responseMap.put("id", userDetails.getId());
    responseMap.put("username", userDetails.getUsername());
    responseMap.put("email", userDetails.getEmail());
    responseMap.put("roles", roles);

    return ResponseEntity.ok().body(responseMap);
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }
    if (!Objects.equals(signUpRequest.getPassword(), signUpRequest.getRepeatPassword())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Password mismatch!"));
    }

    // Create new user's account

    UserEntity userEntity = new UserEntity();
    userEntity.setFirstName(signUpRequest.getFirstName());
    userEntity.setLastName(signUpRequest.getLastName());
    userEntity.setUsername(signUpRequest.getUsername());
    userEntity.setEmail(signUpRequest.getEmail());
    userEntity.setPassword(encoder.encode(signUpRequest.getPassword()));

    userRepository.save(userEntity);



    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
}

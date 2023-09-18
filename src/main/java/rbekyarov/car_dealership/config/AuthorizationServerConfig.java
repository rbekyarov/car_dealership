package rbekyarov.car_dealership.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import rbekyarov.car_dealership.models.entity.UserEntity;
import rbekyarov.car_dealership.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
public class AuthorizationServerConfig  {
@Primary
@Bean
UserDetailsService customUserDetailsService(UserRepository userRepository) {

    return (username) -> {
        // Извличане на информация за потребителя от userRepository по потребителско име
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            // Ако потребителят съществува, връщаме информацията за него
            UserEntity userEntity = userOptional.get();
            UserDetails userDetails = new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toSet());

                }

                @Override
                public String getPassword() {
                    return userEntity.getPassword();
                }

                @Override
                public String getUsername() {
                    return  userEntity.getEmail();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
            return userDetails ;
        } else {
            // Ако потребителят не съществува, генерираме изключение UsernameNotFoundException
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }
    };

}

    @Bean
    AuthenticationManager customAuthenticationManager(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        return authentication -> {
            String username = authentication.getPrincipal() + "";
            String password = authentication.getCredentials() + "";

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (!encoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("Bad credentials");
            }

            if (!userDetails.isEnabled()) {
                throw new DisabledException("User account is not active");
            }
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

            return new UsernamePasswordAuthenticationToken(username, null,authorities );
        };
    }
}


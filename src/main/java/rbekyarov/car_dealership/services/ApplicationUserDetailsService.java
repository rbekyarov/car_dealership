//package rbekyarov.car_dealership.services;
//
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import rbekyarov.car_dealership.models.entity.Role;
//import rbekyarov.car_dealership.models.entity.UserEntity;
//import rbekyarov.car_dealership.repository.UserRepository;
//
//import java.util.List;
//
//public class ApplicationUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public ApplicationUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.
//                findByEmail(username).
//                map(this::map).
//                orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
//    }
//
//    private UserDetails map(UserEntity userEntity) {
//        return new AppUserDetails(
//                        userEntity.getUsername(),
//                        userEntity.getPassword(),
//                extractAuthorities(userEntity),
//                userEntity.getId()
//                );
//    }
//
//    private List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {
//        return  userEntity.
//                getRoles().
//                stream().
//                map(this::mapRole).
//                toList();
//    }
//
//    private GrantedAuthority mapRole(Role role) {
//        return new SimpleGrantedAuthority("ROLE_" + role.getName());
//    }
//}

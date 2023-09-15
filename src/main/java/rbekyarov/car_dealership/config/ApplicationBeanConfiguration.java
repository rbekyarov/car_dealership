package rbekyarov.car_dealership.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;
import rbekyarov.car_dealership.interceptor.RequestProcessingTimeInterceptor;
import rbekyarov.car_dealership.repository.UserRepository;
import rbekyarov.car_dealership.services.ApplicationUserDetailsService;

@EnableWebSecurity
@Configuration
public class ApplicationBeanConfiguration  {
    private final UserRepository userRepository;


    public ApplicationBeanConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(new AntPathRequestMatcher("/api/**","HttpMethod.POST")).permitAll() // Публични ресурси
                                .requestMatchers(new AntPathRequestMatcher("/api/**","HttpMethod.GET")).permitAll() // Публични ресурси
                                .requestMatchers(new AntPathRequestMatcher("/api/auth/**")).authenticated() // Защитени ресурси, изискващи аутентикация

                )
                .cors().disable()
                .csrf().disable()

                .httpBasic(Customizer.withDefaults()); // Използваме базова HTTP аутентикация

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ApplicationUserDetailsService(userRepository);
    }

    @Bean
    public RequestProcessingTimeInterceptor requestProcessingTimeInterceptor() {
        return new RequestProcessingTimeInterceptor();
    }


    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("deletion");
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    }


package com.skillstorm.taxappbackend.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true) 

public class SecurityConfiguration {

 

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authorizeHttpRequests) -> {

                    authorizeHttpRequests
                            

                            .mvcMatchers(HttpMethod.POST, "/users").permitAll() // create user method
                            .mvcMatchers(HttpMethod.GET, "/users").permitAll() // Get all users
                            .mvcMatchers(HttpMethod.GET, "/users/email").permitAll() // login method
                            .mvcMatchers(HttpMethod.PUT, "/users/**").authenticated()// login method
                            .mvcMatchers(HttpMethod.PUT, "/users").authenticated() // update user method
                            .mvcMatchers(HttpMethod.DELETE, "/users").authenticated() // delete user method

                            .mvcMatchers(HttpMethod.POST, "/tax-information").authenticated() // get tax info
                            .mvcMatchers(HttpMethod.GET, "/tax-information").authenticated() // get tax info
                            .mvcMatchers(HttpMethod.PUT, "/tax-information").authenticated() // get tax info
                            .mvcMatchers(HttpMethod.DELETE, "/tax-information").authenticated() // get tax info

                            .mvcMatchers(HttpMethod.GET, "/tax-calculation/**").authenticated() // get tax info
                            .anyRequest().authenticated(); // any other request requires authentication
                });
        http.httpBasic();

        http.csrf((csrf) ->
     
        csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringAntMatchers("/users/**",
                "/tax-information/**"))
                .cors(cors -> {
                    cors.configurationSource(request -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Arrays.asList("http://44.201.48.146:8080"));
                        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                        config.setAllowCredentials(true);
                        config.setMaxAge(3600L); // 1 hour

                        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                        source.registerCorsConfiguration("/**", config);
                        return config;
                    });
                });

        return http.build(); 
    }

  
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
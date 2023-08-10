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
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true) // allow for AOP security checks (prePostEnabled is
                                                                   // true by default)
public class SecurityConfiguration {

    /**
     * be careful about doing research with spring security
     * many methods got deprecated in summer 2022
     * 
     * SecurityConfiguration class used to extend WebSecurityConfigurerAdapter (this
     * is deprecated now)
     * 
     * 
     * antMatchers used to be used instead of mvcMatchers
     * antMatchers are not deprecated but in general mvcMatchers are preferred
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // using the httpSecurity object to configure which endpoints require
        // authentication/authorization
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> {

                    authorizeHttpRequests
                            // allowing all access to /users/hello without authentication

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

        // http.httpBasic(); // uses Basic Authentication instead of formLogin
        /**
         * Cross Site Request Forgery
         * when someone is trying to be you while you are logged in
         * 
         * Spring Security handles this by using a Synchronizer Token pattern
         * - when you do a GET request, the server will generate a token and return it
         * - then in every future rrequest that modifies data (Put, Post, Delete, etc.)
         * you need to include the token in the header
         * 
         * can be disabled with csrf().disable() but this is BAD PRACTICE
         */
        http.csrf((csrf) ->
        // the CSRF filter will check for the csrf token on every modifying reques,
        // except to /users/register

        // this will generate and return a XSRF-TOKEN cookie with a generated value
        // need to include a X-XSRF-TOKEN in your headers with the matching genertated
        // value to validate the user
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

        return http.build(); // builder design pattern
    }

    /**
     * using bCrpyt to encode passwords
     * BCrpyt uses hashing so there is a predictable result each time
     * it will hash the number of times that you specify (cost factor)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
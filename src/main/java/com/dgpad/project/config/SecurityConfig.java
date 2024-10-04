package com.dgpad.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    // Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security Filter Chain Configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/register", "/auth/login").permitAll() // Public endpoints
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Only accessible to ADMIN role
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/perform_login") // POST endpoint for form login
                        .defaultSuccessUrl("/", true) // Redirect to homepage upon successful login
                        .failureUrl("/auth/login?error=true") // Redirect to login page on failure
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/perform_logout") // POST endpoint for logout
                        .logoutSuccessUrl("/auth/login?logout=true") // Redirect to login page on logout
                        .deleteCookies("JSESSIONID") // Delete session cookie on logout
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Configure CSRF token
                ); // Enable CSRF protection with a custom repository

        return http.build();
    }
}
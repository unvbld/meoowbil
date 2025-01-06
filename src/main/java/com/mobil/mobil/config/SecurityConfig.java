package com.mobil.mobil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mobil.mobil.service.UserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailService userDetailService;

    public SecurityConfig(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/register", "/auth/login").permitAll()
                .requestMatchers("/sales/**").hasRole("SALES") // Akses /sales hanya untuk SALES
                .requestMatchers("/cars/**").hasAnyRole("SALES", "USER") // Akses /cars untuk SALES dan USER
                .anyRequest().authenticated() // Akses lainnya memerlukan login
            )
            .formLogin(form -> form
                .loginPage("/auth/login")
                .successHandler(customAuthenticationSuccessHandler()) // Custom redirect
                .failureUrl("/auth/login?error=true")
            )
            .logout(logout -> logout
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/auth/login?logout=true")
            )
            .userDetailsService(userDetailService); // Gunakan UserDetailService
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // Cek role pengguna
            authentication.getAuthorities().forEach(authority -> {
                try {
                    if (authority.getAuthority().equals("ROLE_SALES")) {
                        response.sendRedirect("/sales"); // Redirect ke /sales untuk SALES
                    } else if (authority.getAuthority().equals("ROLE_USER")) {
                        response.sendRedirect("/cars"); // Redirect ke /cars untuk USER
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        };
    }
}



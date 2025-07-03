package com.milkFirst.MilkFirst_Complaint_Management_System.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(csrf -> csrf.disable()).
                authorizeHttpRequests(request -> request
                        .requestMatchers("/user/register","/admin/register")
                        .permitAll()
                        .requestMatchers("/complaint/getAll").hasRole("ADMIN")
                        .requestMatchers("/complaint/add").hasRole("USER")
                        .requestMatchers("/complaint/update/{id}").hasRole("ADMIN")
                        .requestMatchers("/complaint/status-count").hasRole("ADMIN")
                        .requestMatchers("/complaint/getAllByUserNameAdmin").hasRole("ADMIN")
                        .requestMatchers("/complaint/getAllByUserName").hasRole("USER")
                        // .requestMatchers("/complaint/delete").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandler))
                .httpBasic(Customizer.withDefaults())

        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }
}

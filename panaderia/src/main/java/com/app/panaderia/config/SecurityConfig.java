package com.app.panaderia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/contacto", "/enviarMensaje", "/css/**", "/js/**", "/images/**").permitAll() // Permitir acceso libre
                .requestMatchers("/admin/**").hasRole("ADMIN") // Solo restringir /admin/**
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable()) // Desactivar CSRF para evitar bloqueos en formularios POST
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/") // Redirige a la home tras logout
                .permitAll()
            );
        return http.build();
    }

}




package com.app.panaderia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    @Service
    public class EmailService {

        @Autowired
        private JavaMailSender mailSender;

        public void enviarCorreo(String destinatario, String asunto, String mensaje) {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(destinatario);
            email.setSubject(asunto);
            email.setText(mensaje);
            mailSender.send(email);
        }
    }
}




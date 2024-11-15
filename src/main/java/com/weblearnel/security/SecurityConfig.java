package com.weblearnel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(request -> request.getServletPath().equals("/login")).permitAll()
                        .requestMatchers(request -> request.getServletPath().startsWith("/assets/css/")).permitAll()
                        .requestMatchers(request -> request.getServletPath().startsWith("/assets/js/")).permitAll()
                        .requestMatchers(request -> request.getServletPath().startsWith("/assets/images/")).permitAll()

                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").permitAll()
                        .defaultSuccessUrl("/Word-Learning", true)
                        .failureUrl("/login?error=true")
                )
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

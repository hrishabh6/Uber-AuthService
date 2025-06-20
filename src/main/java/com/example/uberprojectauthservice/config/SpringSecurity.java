package com.example.uberprojectauthservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity{

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http    .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/v1/auth/signup/*").permitAll()
                        .requestMatchers("/api/v1/auth/signin/*").permitAll()
                );
        // ...

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

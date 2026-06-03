package com.musiclibrary.notification.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation
        .web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication
        .UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf().disable()

            .authorizeHttpRequests()

            // Admin-only actions
            .requestMatchers(
                "/api/admin/notifications/send",
                "/api/admin/notifications/*")
                .hasRole("ADMIN")

            // Both USER and ADMIN can view notifications
            .requestMatchers(
                "/api/admin/notifications")
                .hasAnyRole("ADMIN", "USER")

            .anyRequest()
            .authenticated()

            .and()

            .addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}
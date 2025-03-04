package com.pragma.traceability.configuration.securityconfig;

import com.pragma.traceability.configuration.securityconfig.jwtconfiguration.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String OWNER_ROLE = "OWNER";
    private static final String CUSTOMER_ROLE = "CUSTOMER";
    private static final String EMPLOYEE_ROLE = "EMPLOYEE";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth-user/login").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/swagger-resources/**").permitAll()
                                .requestMatchers("/webjars/**").permitAll()
                                .requestMatchers("/swagger-ui.html").permitAll()
                                .requestMatchers("/state/save").hasAnyRole(CUSTOMER_ROLE, EMPLOYEE_ROLE)
                                .requestMatchers("/state/consult-traceability").hasRole(CUSTOMER_ROLE)
                                .requestMatchers("/state/consult-eficiency-by-order").hasRole(OWNER_ROLE)
                                .requestMatchers("/state/getEfficiencyAllEmployee").hasRole(OWNER_ROLE)
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}


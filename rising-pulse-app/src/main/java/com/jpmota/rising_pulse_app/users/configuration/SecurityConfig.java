package com.jpmota.rising_pulse_app.users.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**", // O JSON/YAML da especificação OpenAPI
            "/swagger-ui/**",  // O frontend do Swagger UI
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilita a proteção CSRF (comum para APIs REST)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Configura as regras de autorização
                .authorizeHttpRequests(authorize -> authorize
                        // 3. Permite requisições POST para "/user" publicamente
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        // (O / a mais no seu POST, "//user", será normalizado para "/user")
                        .requestMatchers(HttpMethod.POST, "/user/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/user/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/user/**").permitAll()

                        // 4. Exige autenticação para todas as outras requisições
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.sena.enter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        var configuration = new org.springframework.web.cors.CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        var source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
}

    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> 
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/autenticacion/**",
                    "/api/customer/**",
                    "/api/actors/create",
                    "/api/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/api-docs/**",
                    "/api/actors/**"
                ).permitAll()
                .anyRequest().authenticated()
            );

        return http.build();

}
}

// Accede a Swagger UI
//     Una vez que tu app esté corriendo, abre:
//     http://localhost:8080/swagger-ui.html

// Para probar las imgenes poner esto "iVBORw0KGgoAAAANSUhEUgAAAPoAAAD6CAIAAAAHjs1qAAAQAElEQVR4Aey9a7QlyVUe+O0dmXnOuc96V1e3wGPW/PIv7JmxGRuNjARSA5JoIUsgDYv5AawBCRh7vHgYARKGtWwkWOO1WCx7xoDNIIwkHhIsEA8Z8R6QBZLQW+qu7uqurqqu6npX3XvOyYzYe77IvPd2"
package com.spimex.spxfeedmds.general.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    // TODO: rollback.
    private static final String[] AUTH_WHITELIST = {
            "/actuator/**",
            "/test/**",
            "contribute/SPIMEX_MDS2",
            "contribute/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(httpRequest -> httpRequest
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .oauth2ResourceServer(OAuth2 -> OAuth2.jwt(withDefaults()))
                .build();
    }
}

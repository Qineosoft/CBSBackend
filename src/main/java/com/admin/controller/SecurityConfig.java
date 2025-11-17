package com.admin.controller;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						auth -> auth
								.requestMatchers("/set-password", "/api/set-password", "/api/register",
										"/api/public/**", "/swagger-ui/**", "/v3/api-docs/**", "/**")
								.permitAll().anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public Random random() {
		return new Random();
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Configuration
    public class CorsConfig {

        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedOrigins("http://localhost:3000")
                            .allowedMethods("*")
                            .allowedHeaders("*")
                            .allowCredentials(true);
                }
            };
        }
    }
}

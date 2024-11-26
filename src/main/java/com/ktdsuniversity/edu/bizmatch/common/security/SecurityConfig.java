package com.ktdsuniversity.edu.bizmatch.common.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> {
			CorsConfigurationSource source = request -> {
				CorsConfiguration corsConfiguration = new CorsConfiguration();
				
				// 허용을 할 도메인의 목록
				corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
				
				// 허용을 할 메서드의 목록
				corsConfiguration.setAllowedMethods(List.of("POST", "GET", "PUT", "DELETE", "OPTION"));
				corsConfiguration.setAllowedHeaders(List.of("*"));
				
				return corsConfiguration;
			};
			
			cors.configurationSource(source);
		});
	
		http.csrf(csrf -> csrf.ignoringRequestMatchers("/token", "/api/**"));
		return http.build();
	}
}

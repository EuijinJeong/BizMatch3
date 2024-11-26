package com.ktdsuniversity.edu.bizmatch.common.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.ktdsuniversity.edu.bizmatch.common.security.jwt.JsonWebTokenAuthenticationFilter;
import com.ktdsuniversity.edu.bizmatch.member.dao.MemberDao;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
	@Autowired
	private JsonWebTokenAuthenticationFilter jsonWebTokenAuthenticationFilter;
	
	@Autowired
	private MemberDao memberDao;
	
	@Bean
	UserDetailsService securityUserDetailsService() {
		return new SecurityUserDetailsService(memberDao);
	}
	
	@Bean
	@Scope("prototype")
	PasswordEncoder securityPasswordEncoder() {
		return new SecurityPasswordEncoder();
	}
	@Bean
	AuthenticationProvider securityAuthenticationProvider() {
		return new SecurityAuthenticationProvider(
				this.securityUserDetailsService(),
				this.securityPasswordEncoder());
	}
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
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
		http.authorizeHttpRequests(httpRequest->
									httpRequest.requestMatchers("/").permitAll()
												.requestMatchers("/member/signup/**").permitAll()
												.requestMatchers("/bizno/api/ask/**").permitAll()
												.requestMatchers("/member/signin").permitAll()
												.requestMatchers("/member/findpwd").permitAll()
												.requestMatchers("/member/resetpwd").permitAll()
												.requestMatchers("/token").permitAll());
		
	
		http.csrf(csrf -> csrf.ignoringRequestMatchers("/token", "/api/**"));
		return http.build();
	}
}

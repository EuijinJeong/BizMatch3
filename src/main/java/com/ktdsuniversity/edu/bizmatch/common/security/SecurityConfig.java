package com.ktdsuniversity.edu.bizmatch.common.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
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
	
	// 1. SecurityUserDetailsService bean등록.
	@Bean
	UserDetailsService securityUserDetailsService() {
		return new SecurityUserDetailsService(this.memberDao);
	}
	
	// 2. SecurityPasswordEncoder bean등록.
	@Bean
	@Scope("prototype") // 필요할 때 마다 새로운 인스턴스를 생성시키는 애노테이션 ("prototype") -> 이거 쓸 때 자주 없는데 가아끔 있음.
	PasswordEncoder securityPasswordEncoder() {
		return new SecurityPasswordEncoder();
	}
	
	// 3. SecurityAuthenticationProvider bean 등록.
	@Bean
	AuthenticationProvider authenticationProvider() {
		return new SecurityAuthenticationProvider(this.securityUserDetailsService(), this.securityPasswordEncoder());
	}
	
//	/**
//	 * 로그인을 실패했을 때 핸들링을 처리하는 빈 객체이다.
//	 * 
//	 * @return
//	 */
//	@Bean
//	AuthenticationFailureHandler loginFailureHandler() {
//		return new LoginFailureHandler(this.memberDao);
//	}
	
	/**
	 * 아래 url들은 스프링 시큐리티가 절대 개입하지 않는다. -> <sec:*></sec:*> 사용 불가!
	 * 
	 * @return
	 */
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/WEB-INF/views/**")
//						.requestMatchers("/member/login")
//						.requestMatchers("/member/regist/**")
				.requestMatchers("/error/**").requestMatchers("favicon.ico").requestMatchers("/member/**-delete-me")
				.requestMatchers("/js/**").requestMatchers("/css/**");
		
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
		
	
		http.addFilterAfter(this.jsonWebTokenAuthenticationFilter, BasicAuthenticationFilter.class);
		
		http.authorizeHttpRequests(httpRequest ->
		httpRequest.requestMatchers("/api/**").permitAll() // 비밀번호 찾기 페이지.
				  );
		http.csrf(csrf -> csrf.ignoringRequestMatchers("/token", "/api/**"));
		return http.build();
	}
}

package com.ktdsuniversity.edu.bizmatch.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 인증을 수행 -> 아이디로 회원을 찾고 비밀번호로 검증을 수행한다.
 * 아이디로 회원을 찾는다: UserDetailsService -> SecurityDetailsUserService가 수행.
 * 비밀번호로 회원을 찾는다: PasswordEncoder -> SecurityPasswordEncoder가 수행.
 * 수행 결과가 정상이라면 SecurityContext에 인증 정보를 저장시킨다.
 * 
 * AuthorizationFilter 
 * 	-> AuthorizationManager 
 * 		-> 호출.
 */
public class SecurityAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(SecurityAuthenticationProvider.class);

	/**
	 * 인증 회원 정보 조회하기.
	 */
	private UserDetailsService userDetailsService;
	
	/**
	 * 인증 비밀번호 검증하기.
	 */
	private PasswordEncoder passwordEncoder;
	
	public SecurityAuthenticationProvider(UserDetailsService userDetailsService
										, PasswordEncoder passwordEncoder ) {
		
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * support에 정의된 인증 방식으로 인증을 수행한다.
	 * 
	 * SecurityContext => HttpSession을 대체할 인증 정보들이 모여있는 곳.
	 * 
	 * @param authentication 사용자가 인증을 요청한 정보. (이메일, 비번)
	 * @return Authentication support 에 정의된 인증 토큰 -> SecurityContext에 저장 -> 인증 완료.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		/**
		 * 사용자가 전달한 인증 이메일과 비밀번호를 추출. -> parameter: authentication
		 * 인증 이메일로 회원 정보를 조회한다. -> UserDetailsService.loadUserByUserName();
		 * 인증 비밀번호와 회원의 비밀번호를 검증한다. -> PasswordEncoder.matches();
		 * 
		 * SecurityContext에 인증토큰(UsernamePasswordAuthenticationToken)을 저장한다.
		 */
		String requestAuthenticationEmail = authentication.getName();
		String requestAuthenticationPassword = authentication.getCredentials().toString();
		
		// 사용자 이메일 정보 가져옴.
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(requestAuthenticationEmail);
		
		// 데이터베이스에 저장된 암호화된 비밀번호.
		String storedUserPassword = userDetails.getPassword();
		String storedUserSalt = ((SecurityUser) userDetails).getSalt();
		((SecurityPasswordEncoder)this.passwordEncoder).setSalt(storedUserSalt);
		
		boolean isMatchPassword = this.passwordEncoder.matches(requestAuthenticationPassword, storedUserPassword);
		
		if(isMatchPassword) {
			// 인증 컨텍스트에 저장.
			return new UsernamePasswordAuthenticationToken(
					((SecurityUser)userDetails).getMemberVO()
					, storedUserPassword
					, userDetails.getAuthorities());
		} 
		else {
			// 비밀번호 인증 초과 횟수가 ~ 어쩌구
			// 일단 여기에다가 작성하는데 더 좋은 위치가 있음
			throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
	}

	/**
	 * 인증 수단을 정의하는 메서드이다.
	 * UsernamePasswordAuthenticationToken을 사용한다.
	 * 	-> 이메일과 비밀번호로 인증을 수행하는 방식이다... - 이 사람은 바보입니다~-
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		logger.debug("Param token type: {} " , authentication);
		return authentication.equals(UsernamePasswordAuthenticationToken.class);   
	}
}

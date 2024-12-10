package com.ktdsuniversity.edu.bizmatch.common.security.jwt;

import java.time.Duration;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JsonWebTokenProvider {

	@Value("${app.jwt.issuer:hello-spring-security}")
	private String issuer;
	
	@Value("${app.jwt.secretkey:spring-security-secret-key-ramdom-token-key}")
	private String secretKey;
	
	public String generateJwt(Duration duration, MemberVO memberVO) {
		// 1. 토큰의 유효기간을 생성한다. (현재 시간 + duration)
		Date now = new Date();
		Date expiry = new Date(now.getTime() + duration.toMillis());
		
		// 2. 토큰 암/복호화를 위한 키를 생성한다. (secret key) 이용한다.
		SecretKey tokenKey = Keys.hmacShaKeyFor(this.secretKey.getBytes()); 
		
		// 3. 토큰 생성 후 반환한다.
		return Jwts.builder()
				   .issuer(this.issuer) // JWT를 발급한 주체.
				   .subject("SpringSecurityJwtToken") // JWT의 이름.
				   .claim("user", memberVO) // JWT에 포함시킬 회원 정보.
				   .claim("email", memberVO.getEmilAddr())
				   .claim("name", memberVO.getMbrNm())
				   .claim("authority", memberVO.getMbrCtgry())
				   .issuedAt(now) // JWT의 발급 시간.
				   .expiration(expiry) // JWT의 유효기간.
				   .signWith(tokenKey) // JWT 암호화에 사용될 비밀키를 설정하는 부분이다.
				   .compact(); // JWT를 문자열 형태로 변환시킨다
	}
	
	/**
	 * 사용자가 보내준 토큰을 검증해 사용자를 조회한다.
	 * @param jwt 사용자가 보내준 토큰.
	 * @return 토큰 내부에 있는 회원의 정보.
	 * @throws JsonProcessingException 
	 */
	public MemberVO getMemberFromJwt(String jwt) throws JsonProcessingException {
		
		if(jwt == null) {
			return null;
		}
		// 토큰을 검증한다. -> MemberVO (검증 결과)
			// 암호화에 사용된 비밀키를 이용해서 복호화를 진행한다.
		SecretKey tokenKey = Keys.hmacShaKeyFor(this.secretKey.getBytes());
		
		// 복호화의 과정들.
		Claims claims = Jwts.parser()
							.verifyWith(tokenKey) // 암호화된 토큰을 복호화 시킨다.
							.requireIssuer(this.issuer) // 복호화된 토큰이 이 시스템이 만든것인지 검증한다.
							.requireSubject("SpringSecurityJwtToken") // 복호화된 토큰의 제목(이름)이 발급된 토큰의 이름과 같은지 검증한다.
							.build() // 토큰 복호화 진행.
							// FIXME 아래 jwt 파싱하는데 왜 오류가 날까
							.parseSignedClaims(jwt) // 클레임을 끄집어낸다.
							.getPayload();

		// 토큰을 검증하는 과정에서 예외가 발생했을 경우. 아래 두가지가 예외가 발생하는 가장 큰 이유들. (빌드하는 과정에서 아래와 같은 예외가 생길수도 있음)
			// 1. 토큰의 유효 기간이 만료되었을 경우.
			// 2. 토큰이 변조되었을 경우.

		// 검증이 성공했을 경우.
			// MemberVO를 반환.
		
		// 토큰에서 조회된 회원 정보이다.
		Object jwtUser = claims.get("user");
		String email = claims.get("emilAddr", String.class);
		
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(jwtUser);
		MemberVO memberVO = om.readValue(json, MemberVO.class);
		
		return memberVO;
	}
}

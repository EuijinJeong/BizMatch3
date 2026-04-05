package com.ktdsuniversity.edu.bizmatch.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

/**
 * SpringSecurity로 인증할 사용자의 정보를 담고 있는 객체.
 * SpringSecurity로 인증한 사용자의 정보를 담고 있는 객체.
 * SpringSecurity가 사용할 클래스이다. -> 인증 수행.
 * 
 * AuthorizationFilter 
 * 	-> AuthorizationManager 
 * 		-> AuthorizationProvider 
 * 			-> UserDetailService 
 * 				-> 호출.
 */
public class SecurityUser implements UserDetails {

	private static final long serialVersionUID = -8054090806777160864L;

	/**
	 * UserDetailsService를 통해서 아이디(이메일)로 데이터베이스에서 조회된 결과를 가지고 있을 멤버변수.
	 */
	private MemberVO memberVO;
	
	public SecurityUser(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	
	public String getSalt( ) {
		return this.memberVO.getSalt();
	}
	
	public MemberVO getMemberVO() {
		return this.memberVO;
	}
	
	/**
	 * 로그인을 요청한 사용자의 권한 정보를 set한다.
	 * 	-> 로그인 이후 해당 사용자의 권한 정보를 데이터베이스에서 조회한 후, 권한을 부여해야한다.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority (String.valueOf(this.memberVO.getMbrCtgry()))); // 사용자의 권한 정보 추가 
		return authorities;
	}
	
	/**
	 * 로그인을 요청한 사용자의 비밀번호를 반환시키는 역할을 한다.
	 */
	@Override
	public String getPassword() {
		return this.memberVO.getPwd();
	}

	/**
	 * 로그인을 요청한 사용자의 아이디(이메일)를 반환시키는 역할을 한다.
	 */
	@Override
	public String getUsername() {
		return this.memberVO.getEmilAddr();
	}

}

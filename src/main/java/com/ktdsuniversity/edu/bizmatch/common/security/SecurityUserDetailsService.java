package com.ktdsuniversity.edu.bizmatch.common.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ktdsuniversity.edu.bizmatch.member.dao.MemberDao;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

/**
 * SpringSecurity에 인증을 요청한 사용자의 정보를 조회하는 역할을 한다.
 * 
 * 아이디(이메일: UserDetails.getUserName())로만 데이터베이스에서 사용자의 정보를 조회한다.
 * 비밀번호 확인은 다른 클래스의 역할이다.
 * 
 * AuthorizationFilter 
 * 	-> AuthorizationManager 
 * 		-> AuthorizationProvider 
 * 			-> 호출.
 */
public class SecurityUserDetailsService implements UserDetailsService {

	/**
	 * 사용자 정보를 조회할 DAO
	 */
	private MemberDao memberDao;
	
	public SecurityUserDetailsService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		MemberVO memberVO = this.memberDao.selectOneMember(username);
		
		if(memberVO == null) {
			// UserDetailService에서 예외가 던져지면,
			// AuthenticationProvider에서 예외를 처리한다.
			throw new UsernameNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		// UserDetails 인터페이스 타입의 클래스로 계정 정보를 전달한다.
		// SecurityUser is a UserDetails
		return new SecurityUser(memberVO);
	}

}

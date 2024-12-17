package com.ktdsuniversity.edu.bizmatch.common.security.jwt;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.common.beans.Sha;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;
import com.ktdsuniversity.edu.bizmatch.member.dao.MemberDao;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberLoginVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

@RestController
public class JsonWebTokenEntryPoint {

	@Autowired
	private JsonWebTokenProvider jsonWebTokenProvider;
	
	@Autowired
	private MemberDao memberDao;
	
	/**
	 * 로그인 수행 -> jwt 토큰 돌려줌
	 * @param loginMemberVO
	 * @return
	 */
	@PostMapping("/member/signin")
	public ApiResponse generateToken(@RequestBody MemberLoginVO loginMemberVO) {
		
		// 아이디 일치하는지 검사.
		String email = loginMemberVO.getEmilAddr();
		MemberVO memberVO = this.memberDao.selectOneMember(email);
		if(memberVO == null) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("아이디 또는 비밀번호가 일치하지 않습니다.");
			ApiResponse errorResponse = new ApiResponse(HttpStatus.FORBIDDEN);
			errorResponse.setErrors(List.of("아이디 또는 비밀번호가 일치하지 않습니다."));
			return errorResponse;
		}
		
		// 비밀번호 일치하는지 검사.
		String password = loginMemberVO.getPwd();
		Sha sha = new Sha();
		String salt = memberVO.getSalt();
		String encryptedPassword = sha.getEncrypt(password, salt);
		if (!encryptedPassword.equals(memberVO.getPwd())) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("아이디 또는 비밀번호가 일치하지 않습니다.");
			ApiResponse errorResponse = new ApiResponse(HttpStatus.FORBIDDEN);
			errorResponse.setErrors(List.of("아이디 또는 비밀번호가 일치하지 않습니다."));
			return errorResponse;
		}
		
		// 사용자가 활성화 상태인지 검사.
		if(memberVO.getMbrStt() == 0) {
			// 수락 전이라면
		}
		// 토큰 만들어서 돌려준다.
		String jwt = this.jsonWebTokenProvider.generateJwt(Duration.ofHours(8), memberVO);
		return new ApiResponse(jwt);
	}
	
}

package com.ktdsuniversity.edu.bizmatch.common.email.web;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.common.email.service.EmailService;
import com.ktdsuniversity.edu.bizmatch.common.email.vo.EmailVO;
import com.ktdsuniversity.edu.bizmatch.common.email.vo.UserEmailAuthNumVO;
import com.ktdsuniversity.edu.bizmatch.common.utils.XssUtil;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;

@RestController
@RequestMapping("/api")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	/**
	 * 회원가입 인증번호 이메일 전송하는 컨트롤러.
	 * @param email
	 * @return
	 */
	@GetMapping("/email/check/{email}/")
	public Map<String, Object> doCheckEmail(@PathVariable String email) {
		email = XssUtil.clean(email);
		
		//인증번호 발급
		String randomNum = this.emailService.sendEmail(email);
		
		//인증번호 값과 이메일을 가진 VO에 값 셋팅
		UserEmailAuthNumVO userEmailAuthNumVO = new UserEmailAuthNumVO();
		userEmailAuthNumVO.setEmilAddr(email);
		userEmailAuthNumVO.setEmilAddrCnfrmNmbr(randomNum);
		if(randomNum == null) {
			return Map.of("response",false);
		}
		return Map.of("response", true);
	}
	
	/**
	 * 이메일 번호 같은지 확인하는 컨트롤러.
	 * @param emil
	 * @param authNum
	 * @return
	 */
	@GetMapping("/email/authnum/samecheck")
	public Map<String, Object> checkAuthNum(@RequestParam String email, @RequestParam String authNum) {
		
		email = XssUtil.clean(email);
		authNum = XssUtil.clean(authNum);
		
		UserEmailAuthNumVO userEmailAuthNumVO = new UserEmailAuthNumVO();
		userEmailAuthNumVO.setEmilAddr(email);
		userEmailAuthNumVO.setEmilAddrCnfrmNmbr(authNum);
		
		boolean isSame = this.emailService.isSameTempEmailAuthNum(userEmailAuthNumVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("response", isSame);
		
		return resultMap;
	}
	
	/**
	 * 특정 회원에게 이메일을 전송하는 컨트롤러.
	 * @param emailVO
	 * @return
	 */
	@PostMapping("/send/email")
	public ApiResponse sendEmail(@RequestBody EmailVO emailVO) {
		boolean isSend = this.emailService.sendEmailForEtc(emailVO);
		return new ApiResponse(isSend);
	}
}

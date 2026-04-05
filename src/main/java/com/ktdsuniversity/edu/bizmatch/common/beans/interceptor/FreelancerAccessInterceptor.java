package com.ktdsuniversity.edu.bizmatch.common.beans.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FreelancerAccessInterceptor implements HandlerInterceptor{

	public static final Logger logger = LoggerFactory.getLogger(FreelancerAccessInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("_LOGIN_USER_");
		
		// 로그인 하는 사람이 프리랜서 회원인경우.
		if(memberVO.getMbrStt() == 1) {
			// 메인화면으로 리다이랙트.
			response.sendRedirect("/");
			return false;
		}
		return true;
	}
}

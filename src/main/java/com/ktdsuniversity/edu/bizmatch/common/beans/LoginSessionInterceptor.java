package com.ktdsuniversity.edu.bizmatch.common.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginSessionInterceptor implements HandlerInterceptor{
	
	public static final Logger logger = LoggerFactory.getLogger(LoginSessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("_LOGIN_USER_");
		if(memberVO== null) {
			response.sendRedirect("/");
			return false;
		}
		return true;
	}
}

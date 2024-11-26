package com.ktdsuniversity.edu.bizmatch.common.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.bizmatch.accesslog.dao.AccessLogDao;
import com.ktdsuniversity.edu.bizmatch.accesslog.vo.AccessLogVO;
import com.ktdsuniversity.edu.bizmatch.common.utils.RequestUtil;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AccessLogInterceptor implements HandlerInterceptor{
	public static final Logger logger = LoggerFactory.getLogger(AccessLogInterceptor.class);

	private AccessLogDao accessLogDao;
	
	public AccessLogInterceptor(AccessLogDao accessLogDao) {
		this.accessLogDao = accessLogDao;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		AccessLogVO accessLogVO = new AccessLogVO();
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("_LOGIN_USER_");
		
		String packageName = handler.toString().replace("com.ktdsuniversity.edu.bizmatch", "");
		packageName = packageName.substring(0,packageName.lastIndexOf(".")).toUpperCase();
		packageName = packageName.replace(".", "/");
		
		accessLogVO.setAccessEmail(memberVO == null ? null:memberVO.getEmilAddr());
		accessLogVO.setAccessIp(RequestUtil.getIp());
		accessLogVO.setAccessType(packageName);
		accessLogVO.setAccessMethod(request.getMethod());
		accessLogVO.setAccessUrl(request.getRequestURI());
		accessLogVO.setLoginSuccessYn(memberVO == null?"N":"Y");
		
		this.accessLogDao.insertNewAccessLog(accessLogVO);
		
		return true;
	}
}

package com.ktdsuniversity.edu.bizmatch.common.security.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.ktdsuniversity.edu.bizmatch.common.security.SecurityUser;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JsonWebTokenAuthenticationFilter extends OncePerRequestFilter {

	@Value("${app.security.permit-all-urls}")
	private List<String> permitAllUrls;
	
	@Autowired
	private JsonWebTokenProvider jsonWebTokenProvider;
	
	private final AntPathMatcher pathMatcher = new AntPathMatcher();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request
								, HttpServletResponse response
								, FilterChain filterChain)
								throws ServletException, IOException {
		// API 일 때만 동작

		// 1. 사용자가 요청한 URL이 무엇인지 확인.
		String url = request.getServletPath();

//		2. URL이 /api/ 로 시작하는 경우는 API 호출을 한 것.
		if(url.startsWith("/api")) {
			boolean isPermitAllUrl = permitAllUrls.stream().anyMatch(pattern -> pathMatcher.match(pattern, url));
			
			// 3. HttpRequest 에서 header 에 있는 Authorization 에서 값을 읽어온다.==> Json Web Token
			String jwt = request.getHeader("Authorization");
			
			if(!isPermitAllUrl && (jwt==null || jwt.trim().length()==0)) {
				//토큰을 전달하지 않음
				// 클라이언트에게 403번 에러를 전송
				response.sendError(HttpStatus.FORBIDDEN.value());
				return;
			}
//			4. JWT 검증 진행 -> MemberVO를 얻는다.(jsonWebTokenProvider 사용)
			MemberVO memberVO = null;
			try {
				memberVO = this.jsonWebTokenProvider.getMemberFromJwt(jwt);
			}catch(ExpiredJwtException eje){
				// 토큰이 만료된 경우
				ApiResponse errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED);
				errorResponse.setErrors(List.of("인증 토큰이 만료되었습니다. 다시 로그인 후 이용해주세요"));
				
				// errorResponse -> JSON
				Gson gson = new Gson();
				String errorJson = gson.toJson(errorResponse);
				
				// JSON -> Response
				response.setCharacterEncoding("UTF-8");
				response.setContentType(MediaType.APPLICATION_JSON.toString());
				
				PrintWriter out = response.getWriter();
				out.write(errorJson);
				
				return;
			}
			catch(MalformedJwtException | SignatureException mje) {
				mje.printStackTrace();
				// 토큰이 변조된 경우
				ApiResponse errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED);
				errorResponse.setErrors(List.of("인증 토큰이 변조되었습니다. 다시 로그인 후 이용해주세요"));
				
				// errorResponse -> JSON
				Gson gson = new Gson();
				String errorJson = gson.toJson(errorResponse);
				
				// JSON -> Response
				response.setCharacterEncoding("UTF-8");
				response.setContentType(MediaType.APPLICATION_JSON.toString());
				
				PrintWriter out = response.getWriter();
				out.write(errorJson);
				
				return;
			}
			
			if(memberVO != null) {
//				5. MemberVO를 이용해서 인증 절차를 거친다.
//				6. Security Context 에 인증 토큰을 등록처리.
			SecurityUser securityUser = new SecurityUser(memberVO);
			Authentication authentucation =  new UsernamePasswordAuthenticationToken(memberVO,
																					"jwtAuthenticationUser",
																					securityUser.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentucation);
		}
	}
//		다음 필터가 있을 경우, 제어권(실행권)을 넘긴다.
		filterChain.doFilter(request, response);
		
	}

}

package com.ktdsuniversity.edu.bizmatch.common.beans;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ktdsuniversity.edu.bizmatch.accesslog.dao.AccessLogDao;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private AccessLogDao accessLogDao;

	@Value("${app.interceptor.logincheck.addpathpatterns}")
	List<String> checkLoginPathpatternsList;
	
	@Value("${app.interceptor.logincheck.excludepath}")
	List<String> excludeLoginPatternsList;
	
	@Value("${app.interceptor.accesslog.addpathpatterns}")
	List<String> accessLogPathpatternsList;
	
	@Value("${app.interceptor.accesslog.excludepath}")
	List<String> excludeAccessLogPathpatternsList;
	
	@Bean
	Sha createInstance() {
		Sha sha = new Sha();
		return sha;
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/*")
				.addResourceLocations("classpath:/static/css/");
		
		registry.addResourceHandler("/js/*")
				.addResourceLocations("classpath:/static/js/");
		
		registry.addResourceHandler("/img/*")
				.addResourceLocations("classpath:/static/img/");
		
		registry.addResourceHandler("/mailhtml/*")
				.addResourceLocations("classpath:/static/mailTemplate/");
	}
	
	@Override public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginSessionInterceptor())
		.addPathPatterns(this.checkLoginPathpatternsList)
		.excludePathPatterns(this.excludeLoginPatternsList);
		
		registry.addInterceptor(new AccessLogInterceptor(accessLogDao))
		.addPathPatterns(accessLogPathpatternsList)
		.excludePathPatterns(excludeAccessLogPathpatternsList); 
		}
	
	/**
	 * cros 설정을 위한 메서드이다. -의진-
	 */
	@Override
	public void addCorsMappings(final CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET", "POST");
	}
}

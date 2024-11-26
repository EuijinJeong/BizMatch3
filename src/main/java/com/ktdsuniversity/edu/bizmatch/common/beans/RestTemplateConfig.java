package com.ktdsuniversity.edu.bizmatch.common.beans;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 
 */
@Configuration
public class RestTemplateConfig {
	
	/**
	 * r
	 * @param restTemplateBuilder
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		
		return restTemplateBuilder
				.rootUri("https://bizno.net/api/fapi")
				.defaultHeader("key", "amVqMDAxMjI4QGdtYWlsLmNvbSAg")
				.build();
	}

}

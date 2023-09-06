package com.neusoft.elmboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig {
	// 处理跨域请求
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://172.18.182.229:8081") // 允许的跨域ip和端口号
						.allowCredentials(true)
						.allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
						.allowedHeaders("*").maxAge(36000);
			}
		};
	}
}
//http://localhost:8081, 

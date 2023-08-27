package com.neusoft.elm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

//拦截所有请求，包括静态资源，/*的优先级高于/
@WebFilter("/*")
public class CorsFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException { } 
 
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException { 
		HttpServletResponse response = (HttpServletResponse) servletResponse; 
		//注意：这里设置只允许http://localhost:8081进行跨域访问
		//如果想要允许发送Cookie，那么Access-Control-Allow-Origin的值不能为"*"，以免出现安全问题
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081"); 
		//允许发送Cookie
		response.setHeader("Access-Control-Allow-Credentials", "true"); 
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT"); 
		response.setHeader("Access-Control-Max-Age", "3628800"); 
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization"); 
		filterChain.doFilter(servletRequest, servletResponse); 
	} 
  
	@Override
	public void destroy() { }
}
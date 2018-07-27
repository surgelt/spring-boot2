package org.spring.boot2.jdbc.log;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class CorsConfig extends OncePerRequestFilter{

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 防止流读取一次后就没有了, 所以需要将流继续写出去，提供后续使用
		ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
		String json = HttpHelper.getBodyString(requestWrapper);
		System.out.println("json===="+json);
//		filterChain.doFilter(requestWrapper, response);
		filterChain.doFilter(requestWrapper, response);
	}
	
	
}
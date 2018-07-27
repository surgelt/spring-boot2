package org.spring.boot2.xss;

import com.google.common.collect.Maps;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Map;

@Configuration
public class XssConfig extends WebMvcConfigurationSupport {

	/**
	 * xss过滤拦截器
	 */
	@Bean
	public FilterRegistrationBean xssFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = Maps.newHashMap();
		initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
		initParameters.put("isIncludeRichText", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}

	/**
	 * log过滤拦截器
	 */
//	@Bean
//	public FilterRegistrationBean logFilterRegistrationBean() {
//		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//		filterRegistrationBean.setFilter(new LoggingFilter());
//		filterRegistrationBean.setOrder(0);
//		filterRegistrationBean.setEnabled(true);
//		filterRegistrationBean.addUrlPatterns("/*");
//		Map<String, String> initParameters = Maps.newHashMap();
//		initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
//		initParameters.put("isIncludeRichText", "true");
//		filterRegistrationBean.setInitParameters(initParameters);
//		return filterRegistrationBean;
//	}
}
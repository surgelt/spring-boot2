package org.spring.boot2.jdbc.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器 拦截时机 Filter pre -> service -> dispatcher -> preHandle ->controller
 * ->postHandle - > afterCompletion -> FilterAfter Created by yefuliang on
 * 2017/10/23.
 */
public class bgqCommonInterceptorl implements HandlerInterceptor {

	/**
	 * 在controller处理之前首先对请求参数进行处理，以及对公共参数的保存
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("---------------拦截器开始------------------");
		try {
			response.setHeader("Content-type", "application/json;charset=UTF-8");

			String requestMethord = request.getRequestURI();// 请求方法
			if (requestMethord == null) {
				return false;
			}
			System.out.println("===request==="+request);
			BodyReaderHttpServletRequestWrapper request2 = (BodyReaderHttpServletRequestWrapper)request;
			// 获取请求参数
			JSONObject parameterMap = JSON
					.parseObject(request2.getBodyString());
			String dataFrom = String.valueOf(parameterMap.get("dataFrom"));

			System.out.println("==parameterMap==" + parameterMap.toJSONString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("拦截器拦截完成");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		System.out.println("---------------拦截器方法二开始------------------");
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
		System.out.println("---------------拦截器方法三开始------------------");
	}
}
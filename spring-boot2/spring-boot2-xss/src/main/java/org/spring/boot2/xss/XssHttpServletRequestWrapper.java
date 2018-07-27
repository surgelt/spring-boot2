package org.spring.boot2.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

/**
 * 描述 : 跨站请求防范
 *
 * @author
 *
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	/**
	 * 描述 : 构造函数
	 *
	 * @param request
	 *            请求对象
	 */
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (!StringUtils.isEmpty(value)) {
			return HtmlUtils.htmlEscape(value);
		}
		return value;
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if (!StringUtils.isEmpty(value)) {
			return HtmlUtils.htmlEscape(value);
		}
		return value;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values != null) {
			int length = values.length;
			String[] escapseValues = new String[length];
			for (int i = 0; i < length; i++) {
				escapseValues[i] = HtmlUtils.htmlEscape(values[i]);
			}
			return escapseValues;
		}
		return super.getParameterValues(name);
	}

}
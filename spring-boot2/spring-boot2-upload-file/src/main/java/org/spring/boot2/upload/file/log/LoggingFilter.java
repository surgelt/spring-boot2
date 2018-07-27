package org.spring.boot2.upload.file.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

//@Component
/**
 * 1. enctype=application/x- www-form-urlencoded
 * 
 * 这种编码方式是默认的编码方式。
 * 
 * 编码后的结果通常是field1=value2&field2=value2&… 的形式，如 name=aaaa&password=bbbb。
 * 
 * 通常使用的表单也是采用这种方式编码的，Servlet 的 API 提供了对这种 编码方式解码的支持，只需要调用 ServletRequest 类中的
 * getParameter()方法就可以得到用户表单中的字段和数据。
 * 
 * 不足：
 * 
 * 这种编码方式（ application/x-www-form-urlencoded ）虽然简单，但对于传输大块的二进制数据显得力不从心。
 * 
 * 
 * 
 * 2. 对于传输大块的二进制数这类数据，浏览器采用了另一种编码方式，即 "multipart/form-data" 的编码方式：
 * 
 * 浏览器可以很容易将表单内的数据和文件放在一起发送。这种编码方式先定义好一个不可能在数据中出现的字符串作为
 * 分界符，然后用它将各个数据段分开，而对于每个数据段都对应着 HTML 页面表单中的一个 Input 区，包括一个 content-disposition
 * 属性，说明了这个数据段的一些信息，如果这个数据段的内容是一个文件，还会有 Content-Type
 * 属性，然后就是数据本身，如果以这种方式提交数据就要用request.getInputStream()或request.getReader()得到
 * 提交的数据，用 request.getParameter()是得不到提交的数据的。
 * 
 * 
 * 
 * 3.需要注意的是：
 * 
 * request.getParameter()、
 * request.getInputStream()、request.getReader()这三种方法是有冲突的，因为流只能被读一次。 比如：
 * 当form表单内容采用
 * enctype=application/x-www-form-urlencoded编码时，先通过调用request.getParameter()
 * 方法得到参数后，再调用request.getInputStream()或request.getReader()已经得不到流中的内容，因为在调用
 * request.getParameter()时系统可能对表单中提交的数据以流的形式读了一次,反之亦然。
 * 
 * 当form表单内容采用enctype=multipart/form-data编码时，即使先调用request.getParameter()也得不到数据，所以这时调用request.getParameter()方法对
 * request.getInputStream()或request.getReader()没有冲突，即使已经调用了
 * request.getParameter()方法也可以通过调用request.getInputStream()或request.getReader()得
 * 到表单中的数据,而request.getInputStream()和request.getReader()在同一个响应中是不能混合使用的,如果混合使用就会抛异常。
 * 
 * @author liutao
 *
 */
public class LoggingFilter extends OncePerRequestFilter {

	protected static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
	private static final String REQUEST_PREFIX = "Request: ";
	private static final String RESPONSE_PREFIX = "Response: ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			String requestId = UUID.randomUUID().toString().replaceAll("-", "");
			if (!isMultipart(request) && !isBinaryContent(request))
				request = new RequestWrapper(requestId, request);
			response = new ResponseWrapper(requestId, response);
			logRequest(request, requestId);

			// request.getAttributeNames();
		}

		try {
			filterChain.doFilter(request, response);
			// response.flushBuffer();
		} finally {
			if (logger.isDebugEnabled()) {
				logResponse((ResponseWrapper) response);
			}
		}

	}

	private void logRequest(final HttpServletRequest request, final String requestId) {
		StringBuilder msg = new StringBuilder();
		msg.append(REQUEST_PREFIX);
		if (request instanceof RequestWrapper) {
			msg.append("request id=").append(((RequestWrapper) request).getId()).append("; ");
		} else {
			msg.append("request id=").append(requestId).append("; ");
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			msg.append("session id=").append(session.getId()).append("; ");
		}
		if (request.getMethod() != null) {
			msg.append("method=").append(request.getMethod()).append("; ");
		}
		if (request.getContentType() != null) {
			msg.append("content type=").append(request.getContentType()).append("; ");
		}
		msg.append("uri=").append(request.getRequestURI());
		if (request.getQueryString() != null) {
			msg.append('?').append(request.getQueryString());
		}

		if (request instanceof RequestWrapper && !isMultipart(request) && !isBinaryContent(request)) {
			RequestWrapper requestWrapper = (RequestWrapper) request;
			try {
				String charEncoding = requestWrapper.getCharacterEncoding() != null
						? requestWrapper.getCharacterEncoding() : "UTF-8";
				msg.append("; payload=").append(new String(requestWrapper.toByteArray(), charEncoding));
			} catch (UnsupportedEncodingException e) {
				logger.warn("Failed to parse request payload", e);
			}

		} else if (!isBinaryContent(request)) {
			msg.append("; payload=").append(JSON.toJSONString(request.getParameterMap()));
		}
		logger.debug(msg.toString());
	}

	private boolean isBinaryContent(final HttpServletRequest request) {
		if (request.getContentType() == null) {
			return false;
		}
		return request.getContentType().startsWith("image") || request.getContentType().startsWith("video")
				|| request.getContentType().startsWith("audio");
	}

	private boolean isMultipart(final HttpServletRequest request) {
		return request.getContentType() != null && (request.getContentType().startsWith("multipart/form-data")
				|| request.getContentType().startsWith("application/x-www-form-urlencoded"));
	}

	private void logResponse(final ResponseWrapper response) {
		StringBuilder msg = new StringBuilder();
		msg.append(RESPONSE_PREFIX);
		msg.append("request id=").append((response.getId()));
		try {
			msg.append("; payload=").append(new String(response.toByteArray(), response.getCharacterEncoding()));
		} catch (UnsupportedEncodingException e) {
			logger.warn("Failed to parse response payload", e);
		}
		logger.debug(msg.toString());
	}

}

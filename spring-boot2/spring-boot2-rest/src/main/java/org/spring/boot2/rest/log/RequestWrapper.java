package org.spring.boot2.rest.log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot2.rest.xss.JsoupUtil;
import org.springframework.util.StreamUtils;

public class RequestWrapper extends HttpServletRequestWrapper {
	private static Logger logger = LoggerFactory.getLogger(RequestWrapper.class);

	private byte[] body;
	private String requestId;

	public RequestWrapper(String requestId, HttpServletRequest request) throws IOException {
		super(request);
		// body = IOUtils.toByteArray(request.getInputStream());
		initBody(request);

		this.requestId = requestId;

	}

	private void initBody(HttpServletRequest request) throws IOException {
		if (body == null || body.length == 0) {
			// body = getBodyString(request).getBytes(Charset.forName("UTF-8"));
			byte[] bodyTemp = StreamUtils.copyToByteArray(request.getInputStream());

			if (bodyTemp != null && bodyTemp.length > 0) {
				String b = new String(bodyTemp, Charset.forName("UTF-8"));
				b = JsoupUtil.clean(b);// 防XSS攻击
				body = b.getBytes(Charset.forName("UTF-8"));
			}
		}

		if ((body == null || body.length == 0)) {
			body = new String().getBytes();

		}
		// logger.debug("body====" + new String(body));

	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		logger.debug("===getInputStream===" + body.length);

		// 这里从body里面直接读了，没有去读inputStream了，很巧妙的方式
		final ByteArrayInputStream bais = new ByteArrayInputStream(body);

		return new ServletInputStream() {

			@Override
			public int read() throws IOException {
				return bais.read();
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {

			}
		};
	}

	public String getId() {
		// TODO Auto-generated method stub
		return this.requestId;
	}

	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return this.body;
	}
}

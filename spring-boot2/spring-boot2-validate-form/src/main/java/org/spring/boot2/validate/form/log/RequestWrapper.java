package org.spring.boot2.validate.form.log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot2.validate.form.xss.JsoupUtil;
import org.springframework.util.StreamUtils;

public class RequestWrapper extends HttpServletRequestWrapper {
	private static Logger logger = LoggerFactory.getLogger(RequestWrapper.class);

	private byte[] body;
	private long requestId;

	// public RequestWrapper(HttpServletRequest request) throws IOException {
	// super(request);
	// body = getBodyString(request).getBytes(Charset.forName("UTF-8"));
	// }

	public RequestWrapper(long requestId, HttpServletRequest request) throws IOException {
		super(request);
		// body = getBodyString(request).getBytes(Charset.forName("UTF-8"));
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
				b = JsoupUtil.clean(b);
				body = b.getBytes(Charset.forName("UTF-8"));
			}
		}

		if ((body == null || body.length == 0)) {
			body = new String().getBytes();

		}
		System.out.println("body====" + new String(body));

	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		System.out.println("===getInputStream===" + body.length);

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

	// public String getBodyString(ServletRequest request) {
	// StringBuilder sb = new StringBuilder();
	// InputStream inputStream = null;
	// BufferedReader reader = null;
	// try {
	// inputStream = request.getInputStream();
	// reader = new BufferedReader(new InputStreamReader(inputStream,
	// Charset.forName("UTF-8")));
	// String line = "";
	// while ((line = reader.readLine()) != null) {
	// sb.append(line);
	// }
	// } catch (IOException e) {
	// logger.warn("处理异常", e);
	// } finally {
	// if (inputStream != null) {
	// try {
	// inputStream.close();
	// } catch (IOException e) {
	// logger.warn("处理异常", e);
	// }
	// }
	// if (reader != null) {
	// try {
	// reader.close();
	// } catch (IOException e) {
	// logger.warn("处理异常", e);
	// }
	// }
	// }
	// return sb.toString();
	// }

	public long getId() {
		// TODO Auto-generated method stub
		return this.requestId;
	}

	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return this.body;
	}
}

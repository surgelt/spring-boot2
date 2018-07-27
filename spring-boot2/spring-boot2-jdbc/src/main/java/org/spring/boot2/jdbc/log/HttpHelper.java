package org.spring.boot2.jdbc.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpHelper {

	/**
	 * 获取请求Body
	 * 
	 * @param request
	 * @return
	 */
	private static Logger logger = LoggerFactory.getLogger(HttpHelper.class);

	public static String getBodyString(ServletRequest request) {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			System.out.println("=====sb===="+sb);
		} catch (IOException e) {
			logger.warn("getBodyString出现问题！");
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
package org.spring.boot2.rest;

/**此框架支持如下几点：
 * 1.统一日志拦截器记录
 * 2.防xss攻击，过滤代码字符
 * 3.统一格式化输出
 * 4.参数校验
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ModelAttribute;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
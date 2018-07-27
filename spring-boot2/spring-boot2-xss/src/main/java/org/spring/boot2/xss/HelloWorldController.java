package org.spring.boot2.xss;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class HelloWorldController {
	@RequestMapping("/hello")
	public String index(String name) {
		System.out.println("=======name====" + name);
		return name;
	}

	@RequestMapping("/hello2")
	public String index() {
		// System.out.println("=======name===="+name);
		return "Hello World";
	}

	@PostMapping("/p")
	public String pst(@Valid @RequestBody PersonForm personForm, BindingResult bindingResult) {
		// logger.debug(bindingResult.toString());
		System.out.println("personForm===" + personForm);
		if (bindingResult.hasErrors()) {
			System.out.println("bindingResult===" + bindingResult);
			return "form";
		}

		return "results";
	}

	public static void main(String[] args) {
		String value = "  <a href=\"http://www.baidu.com/a\" onclick=\"alert(1);\">sss</a><script>alert(0);</script>sss   ";
		String s = HtmlUtils.htmlEscape(value);
		System.out.println("ss==" + s);
	}
}
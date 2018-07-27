package org.spring.boot2.validate.form.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot2.validate.form.entity.PersonForm;
import org.spring.boot2.validate.form.log.LoggingFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import java.util.List;

import javax.validation.Valid;

@Controller
public class WebController {
	protected static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@GetMapping("/")
	public String showForm(PersonForm personForm) {
		return "form";
	}

	@PostMapping("/")
	public String checkPersonInfo(@Valid @ModelAttribute PersonForm personForm, BindingResult bindingResult) {
		logger.debug(bindingResult.toString());
		System.out.println("personForm===" + personForm);
		if (bindingResult.hasErrors()) {
			System.out.println("bindingResult===" + bindingResult);
			return "form";
		}

		return "results";
	}
	
	@PostMapping("/p")
	public String pst(@Valid @RequestBody PersonForm personForm, BindingResult bindingResult) {
		logger.debug(bindingResult.toString());
		System.out.println("personForm===" + personForm);
		if (bindingResult.hasErrors()) {
			System.out.println("============error start===========");
			List<ObjectError> er = bindingResult.getAllErrors();
			for (ObjectError objectError : er) {
				System.out.println(JSON.toJSONString(objectError));
			}
			
			System.out.println("============error emd===========");
			return "form";
		}

		return "results";
	}
	
	@PostMapping("/p2")
	public String pst2(@RequestParam String name) {
//		logger.debug(bindingResult.toString());
		System.out.println("name===" + name);
		

		return "results";
	}
	
}
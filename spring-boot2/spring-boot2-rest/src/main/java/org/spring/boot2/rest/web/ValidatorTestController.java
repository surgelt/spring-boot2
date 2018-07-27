package org.spring.boot2.rest.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot2.rest.entity.Leader;
import org.spring.boot2.rest.msg.result.CommonResult;
import org.spring.boot2.rest.msg.result.ResultUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

/**
 * 关于Validator使用测试
 *
 */

@RestController
@RequestMapping("validator")
public class ValidatorTestController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorTestController.class);

	/**
	 * validate验证测试
	 *
	 * @param leader
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/test")
	public CommonResult testSimpleValidate(@Valid @RequestBody Leader leader, BindingResult bindingResult) {
		LOGGER.debug("ReqParams:{}", JSON.toJSONString(leader));
		CommonResult result = validParams(bindingResult);
		if (!result.isSuccess()) {
			return result;
		}
		return ResultUtil.returnSuccess("");
	}

	@GetMapping("/test")
	public CommonResult testGet(@Valid Leader leader, BindingResult bindingResult) {
		LOGGER.debug("ReqParams:{}", JSON.toJSONString(leader));
		CommonResult result = validParams(bindingResult);
		if (!result.isSuccess()) {
			return result;
		}
		return ResultUtil.returnSuccess("");
	}

	@GetMapping("/test2")
	public CommonResult testGet(String name) {
		LOGGER.debug("ReqParams:{}", JSON.toJSONString(name));

		return ResultUtil.returnSuccess("");
	}

	@PostMapping("/test3")
	public CommonResult testSimpleValidate3(@Valid @ModelAttribute Leader leader, BindingResult bindingResult) {
		LOGGER.debug("ReqParams:{}", JSON.toJSONString(leader));
		CommonResult result = validParams(bindingResult);
		if (!result.isSuccess()) {
			return result;
		}
		return ResultUtil.returnSuccess("");
	}

	@PostMapping("/test4")
	public CommonResult testSimpleValidate3(String leader) {
		LOGGER.debug("ReqParams:{}", JSON.toJSONString(leader));

		return ResultUtil.returnSuccess("");
	}
}
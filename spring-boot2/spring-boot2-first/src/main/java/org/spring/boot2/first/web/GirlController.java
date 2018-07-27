package org.spring.boot2.first.web;

import java.util.List;

import javax.validation.constraints.Min;

import org.spring.boot2.first.entity.Girl;
import org.spring.boot2.first.service.GirlRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class GirlController {

	@Autowired
	private GirlRep girlRep;

	/**
	 * 查询所有女生列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/girls", method = RequestMethod.GET)
	public List<Girl> getGirlList() {
		return girlRep.findAll();
	}

	/**
	 * 添加一个女生
	 * 
	 * @param cupSize
	 * @param age
	 * @return
	 */
	@RequestMapping(value = "/girls", method = RequestMethod.POST)
	public Girl addGirl(@RequestParam("cupSize") String cupSize,
			@Min(value = 0, message = "必须大于0") @RequestParam("age") Integer age) {
		Girl girl = new Girl();
		girl.setAge(age);
		girl.setCupSize(cupSize);
		return girlRep.save(girl);
	}

}

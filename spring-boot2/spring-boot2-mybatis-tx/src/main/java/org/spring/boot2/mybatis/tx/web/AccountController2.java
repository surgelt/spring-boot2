package org.spring.boot2.mybatis.tx.web;

import org.spring.boot2.mybatis.tx.service.AccountService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
//@MapperScan("org.spring.boot2.mybatis.tx.dao")
public class AccountController2 {
	@Autowired
	AccountService2 accountService;

	@RequestMapping(value = "transfer", method = RequestMethod.GET)
	public void transfer() {
		accountService.transfer();
	}
}
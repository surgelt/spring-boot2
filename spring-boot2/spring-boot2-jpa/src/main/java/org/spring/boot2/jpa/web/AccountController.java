package org.spring.boot2.jpa.web;

import java.util.List;
import java.util.Optional;

import org.spring.boot2.jpa.dao.AccountDao;
import org.spring.boot2.jpa.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountDao accountDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Account> getAccounts() {
		return accountDao.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Account> getAccountById(@PathVariable("id") int id) {
		return accountDao.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "money", required = true) double money) {
		Account account = new Account();
		account.setMoney(money);
		account.setName(name);
		account.setId(id);
		Account account1 = accountDao.saveAndFlush(account);

		return account1.toString();

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postAccount(@RequestParam(value = "name") String name, @RequestParam(value = "money") double money) {
		Account account = new Account();
		account.setMoney(money);
		account.setName(name);
		Account account1 = accountDao.save(account);
		return account1.toString();

	}

}
package org.spring.boot2.mybatis.tx.service;

import org.spring.boot2.mybatis.tx.dao.AccountMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService2 {
	@Autowired
	AccountMapper2 accountMapper2;

	@Transactional
	public void transfer() throws RuntimeException {
		accountMapper2.update(100, 1);// 用户1减10块 用户2加10块
		int i = 1 / 0;
		accountMapper2.update(110, 2);
	}
}
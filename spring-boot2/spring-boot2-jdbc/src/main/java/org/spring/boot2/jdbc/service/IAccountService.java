package org.spring.boot2.jdbc.service;

import java.util.List;

import org.spring.boot2.jdbc.entity.Account;

public interface IAccountService {
	int add(Account account);

	int update(Account account);

	int delete(int id);

	Account findAccountById(int id);

	List<Account> findAccountList();

}
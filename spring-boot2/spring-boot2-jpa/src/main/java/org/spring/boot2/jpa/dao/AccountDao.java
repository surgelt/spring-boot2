package org.spring.boot2.jpa.dao;

import org.spring.boot2.jpa.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao  extends JpaRepository<Account,Integer> {
}
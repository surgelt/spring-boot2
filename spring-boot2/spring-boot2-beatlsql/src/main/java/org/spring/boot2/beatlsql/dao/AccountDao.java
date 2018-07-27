package org.spring.boot2.beatlsql.dao;

import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.spring.boot2.beatlsql.entity.Account;

public interface AccountDao extends BaseMapper<Account> {

	@SqlStatement(params = "name")
	Account selectAccountByName(String name);
}
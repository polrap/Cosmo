package com.cosmo.web.common.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cosmo.web.common.dao.AccountDAO;
import com.cosmo.web.common.domain.Account;
import com.cosmo.web.common.mapper.AccountMapper;

@Repository
public class AccountDAOImpl implements AccountDAO{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public Account findByAccount(String email) {
		return sqlSession.getMapper(AccountMapper.class).findByAccount(email);
	}

	@Override
	public int countEmail(String email) {
		return sqlSession.getMapper(AccountMapper.class).countEmail(email);
	}

	@Override
	public int countPhoneNum(String phoneNum) {
		return  sqlSession.getMapper(AccountMapper.class).countPhoneNum(phoneNum);
	}

	@Override
	public int register(Account account) {
		return  sqlSession.getMapper(AccountMapper.class).register(account);
	}
}

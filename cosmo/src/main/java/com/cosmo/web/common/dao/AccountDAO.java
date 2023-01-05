package com.cosmo.web.common.dao;

import com.cosmo.web.common.domain.Account;

public interface AccountDAO {
	
	Account findByAccount(String email);
	
	int countEmail(String email);
	
	int countPhoneNum(String phoneNum);
	
	int register(Account account);
}

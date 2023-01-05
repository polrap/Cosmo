package com.cosmo.web.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cosmo.web.common.domain.Account;

@Repository
@Mapper
public interface AccountMapper {
	
	Account findByAccount(String email);
	
	int countEmail(String email);
	
	int countPhoneNum(String phoneNum);
	
	int register(Account account);
}

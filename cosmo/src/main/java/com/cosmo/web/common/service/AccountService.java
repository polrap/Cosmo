package com.cosmo.web.common.service;

import java.util.Map;

import com.cosmo.web.api.VO.MemberVO;
import com.cosmo.web.common.domain.Account;

public interface AccountService {

	Map<String, String> validate(MemberVO memberVO);
	
	Account findByAccount(String email);
	
	int countEmail(String email);
	
	int countPhoneNum(String phoneNum);
	
	int register(Account account);
}

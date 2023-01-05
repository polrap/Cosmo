package com.cosmo.web.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.cosmo.web.api.VO.MemberVO;
import com.cosmo.web.common.dao.AccountDAO;
import com.cosmo.web.common.domain.Account;
import com.cosmo.web.common.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private ReloadableResourceBundleMessageSource res;
	
	@Override
	public Account findByAccount(String email) {
		return accountDAO.findByAccount(email);
	}

	@Override
	public int countEmail(String email) {
		return accountDAO.countEmail(email);
	}

	@Override
	public int countPhoneNum(String phoneNum) {
		return accountDAO.countPhoneNum(phoneNum);
	}

	@Override
	public int register(Account account) {
		return accountDAO.register(account);
	}

	@Override
	public Map<String, String> validate(MemberVO memberVO) {
		
		Map<String, String> validatorResult = new HashMap<>();	
		
		if(memberVO.getEmail().isBlank()) {
			String validKeyName = String.format("valid_email");
			validatorResult.put(validKeyName, res.getMessage("Blank.email", null, null));
		}
		else if(!memberVO.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$")) {
			String validKeyName = String.format("valid_email");
			validatorResult.put(validKeyName, res.getMessage("Pattern.email", null, null));
		}else if(0<countEmail(memberVO.getEmail())) {
			String validKeyName = String.format("valid_email");
			validatorResult.put(validKeyName, res.getMessage("already.email", null, null));
		}
		
		if(memberVO.getPhoneNum().isBlank()) {
			String validKeyName = String.format("valid_phone");
			validatorResult.put(validKeyName, res.getMessage("Blank.phone", null, null));
		}else if(!memberVO.getPhoneNum().matches("^(\\d{10,11}|\\d{3}-\\d{3,4}-\\d{4})$")) {
			String validKeyName = String.format("valid_phone");
			validatorResult.put(validKeyName, res.getMessage("Pattern.phone", null, null));
		}else if(0<countPhoneNum(memberVO.getPhoneNum())) {
			String validKeyName = String.format("valid_phone");
			validatorResult.put(validKeyName, res.getMessage("already.phonenumber", null, null));
		}
		
		if(memberVO.getPw().isBlank()) {
			String validKeyName = String.format("valid_pw");
			validatorResult.put(validKeyName, res.getMessage("Blank.password", null, null));
		}else if(!memberVO.getPw().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$")) {
			String validKeyName = String.format("valid_pw");
			validatorResult.put(validKeyName, res.getMessage("Pattern.password", null, null));
		}
		
//		if(memberVO.getPw().isBlank()) {
//			String validKeyName = String.format("valid_pw");
//			validatorResult.put(validKeyName, res.getMessage("Blank.password", null, null));
//		}else if(memberVO.getPw().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$")) {
//			String validKeyName = String.format("valid_pw");
//			validatorResult.put(validKeyName, res.getMessage("Pattern.password", null, null));
//		}
		
		if(memberVO.getConfirmPw().isBlank()) {
			String validKeyName = String.format("valid_confirmPw");
			validatorResult.put(validKeyName, res.getMessage("Blank.password", null, null));
		}else if(!memberVO.getConfirmPw().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$")) {
			String validKeyName = String.format("valid_confirmPw");
			validatorResult.put(validKeyName, res.getMessage("Pattern.password", null, null));
		}else if(!memberVO.getConfirmPw().equals(memberVO.getPw())) {
			String validKeyName = String.format("valid_confirmPw");
			validatorResult.put(validKeyName, res.getMessage("Notequal.passwordtoconfirm", null, null));
		}
		
		if(memberVO.getUserName().isBlank()) {
			String validKeyName = String.format("valid_userName");
			validatorResult.put(validKeyName, res.getMessage("Blank.name", null, null));
		}
			
		
		return validatorResult;
	}

}

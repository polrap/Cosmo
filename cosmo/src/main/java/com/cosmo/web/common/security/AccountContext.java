package com.cosmo.web.common.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.cosmo.web.common.domain.Account;

public class AccountContext  extends User{

	private  Account account;

	public AccountContext(Account account, Collection<? extends GrantedAuthority> authorities) {
		super(account.getEmail() ,account.getPw(),authorities);
		this.account=account;
	}
	
	public Account getAccount() {
		return account;
	}

}

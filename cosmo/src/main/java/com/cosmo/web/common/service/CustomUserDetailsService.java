package com.cosmo.web.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cosmo.web.common.domain.Account;
import com.cosmo.web.common.security.AccountContext;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private Account account;
	
	@Autowired
	private AccountService accountService;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		account  = accountService.findByAccount(email);
		if(account ==null) {
			throw new UsernameNotFoundException("사용자 못찾음");
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("admin"));
		
		AccountContext acounntContext = new AccountContext(account, roles);
		
		return acounntContext;	}

}

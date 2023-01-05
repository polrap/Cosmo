package com.cosmo.web.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider  implements AuthenticationProvider{

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName=authentication.getName();
		String pw = (String)authentication.getCredentials(); 

		AccountContext accountContext= (AccountContext)userDetailsService.loadUserByUsername(userName);
		if(passwordEncoder.matches(pw, accountContext.getAccount().getPw())) {
			throw new BadCredentialsException("BadCredentialsException");
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());
		
		
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}

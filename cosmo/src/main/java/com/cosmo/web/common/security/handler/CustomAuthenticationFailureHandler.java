package com.cosmo.web.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.cosmo.web.common.security.AccountContext;

@Component
public class CustomAuthenticationFailureHandler  extends SimpleUrlAuthenticationFailureHandler{

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		 
		String errorMessage ="Invalid Username or Password";
		
		if(exception instanceof BadCredentialsException) {
			errorMessage="Invalid Username or Password";
		}else if(exception instanceof InsufficientAuthenticationException) {
			errorMessage="Invalid Secret key";
		}
		
		setDefaultFailureUrl("/login?error=true&exception=" + errorMessage);
		
		super.onAuthenticationFailure(request, response, exception);
	}

}

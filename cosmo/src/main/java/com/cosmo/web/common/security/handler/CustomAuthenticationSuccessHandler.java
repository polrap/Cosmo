package com.cosmo.web.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		setDefaultTargetUrl("/");
		
		SavedRequest saveReq= requestCache.getRequest(request, response);
		if(saveReq!=null) {
			String targetUrl = saveReq.getRedirectUrl();
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}else {
			redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
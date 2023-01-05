package com.cosmo.web.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationSuccessHandler customAutneAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler customAuthenticationFailureHandler;

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		 return (web) -> web.ignoring().antMatchers("/js/**");
				 /*.requestMatchers(PathRequest.toStaticResources().atCommonLocations());*/
	}

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());

	}

	private AuthenticationProvider authenticationProvider() {
		return new CustomAuthenticationProvider();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
			.authorizeRequests()
			.antMatchers("/","/**","/register","/login*").permitAll()
			.antMatchers("/ganre","/recommend").hasRole("member")
		.and()	
			.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/")
			.usernameParameter("email")
			.passwordParameter("pw")
			.successHandler(customAutneAuthenticationSuccessHandler)
			.failureHandler(customAuthenticationFailureHandler)
			.and()
			.logout()
			.logoutUrl("/logout")
			.deleteCookies("remember-me","JSESSIONID")
			.logoutSuccessUrl("/")
			.permitAll()
		.and()
				.rememberMe()
				.rememberMeParameter("remember")
				.tokenValiditySeconds(3600)
				.userDetailsService(userDetailsService);
			
		
	
			

		http.rememberMe().rememberMeParameter("remember").alwaysRemember(false);

		return http.build();
	}
}

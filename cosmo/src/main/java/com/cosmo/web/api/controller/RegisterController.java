package com.cosmo.web.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cosmo.web.api.VO.MemberVO;
import com.cosmo.web.common.domain.Account;
import com.cosmo.web.common.service.AccountService;

@Controller
public class RegisterController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value = "/register")
	public String regist(@ModelAttribute("MemberVO") MemberVO memberVO) {
		return "/register/register";
	}
	
	@PostMapping(value="/register")
	public ModelAndView postRegist(@ModelAttribute("MemberVO") MemberVO memberVO) {
		ModelAndView mv= new ModelAndView();

		Account account = new Account(memberVO);
		account.setPw(passwordEncoder.encode(account.getPw()));
		
		boolean validFlag=true;
		Map<String, String> validatorResult = new HashMap<>();
		
		validatorResult=accountService.validate(memberVO);
		if(!validatorResult.isEmpty()) {
			validFlag=false;
			mv.setViewName("/register/register");
			mv.addObject("valid",validatorResult);
		}
		if(validFlag) {
		mv.setViewName("redirect:/");	
		accountService.register(account);
		}
		return mv;
	}
}

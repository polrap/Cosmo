package com.cosmo.web.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cosmo.web.api.VO.MemberVO;

@Controller
public class LoginController {

	@GetMapping(value="/login")
	public ModelAndView login(@ModelAttribute("MemberVO") MemberVO memberVO, @RequestParam(value="error",required = false) String error,
						@RequestParam(value="exception",required = false) String exception,Model model) {
		
		ModelAndView mv= new ModelAndView();
		mv.addObject("exception",exception);
		mv.addObject("error",error);
		mv.addObject("MemberVO", memberVO);
		mv.setViewName("/login/login");
		
		return mv;
	}
	
	@PostMapping(value="/login")
	public ModelAndView postLogin(@ModelAttribute("MemberVO") MemberVO memverVO) {
		ModelAndView mv= new ModelAndView();
		
		
		
		return mv;
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication !=null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return "redirect:/";
	}
	
	@GetMapping(value="/loginpop")
	public String loginPopup(){
		return "/login/loginPopup";
	}
	
	@GetMapping(value="/logoutpop")
	public String logoutpop(){
		return "/logout/logoutPopup";
	}
	
	@ResponseBody
	@GetMapping(value="/login/kakao")
	public void  kakaoCallback(@RequestParam String code) {

        System.out.println(code);

	}
}

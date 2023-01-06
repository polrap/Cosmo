package com.cosmo.web.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cosmo.web.api.VO.MemberVO;
import com.cosmo.web.common.domain.Account;
import com.cosmo.web.common.service.AccountService;
import com.cosmo.web.common.service.impl.KakaoService;

@RestController
public class LoginController {
	
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private Account account;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv= new ModelAndView();
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication !=null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@GetMapping(value="/loginpop")
	public ModelAndView loginPopup(){
		ModelAndView mv= new ModelAndView();
		mv.setViewName("/login/loginPopup");
		return mv;
	}
	
	@GetMapping(value="/logoutpop")
	public ModelAndView logoutpop(){
		ModelAndView mv= new ModelAndView();
		mv.setViewName("/logout/logoutPopup");
		return mv;
	}
	
	
	@GetMapping(value="/login/kakao")
	public @ResponseBody ModelAndView  kakaoCallback(@RequestParam String code, HttpSession session) throws IOException {
	
		String kakaoToken = kakaoService.getReturnAccessToken(code);
		Map<String, Object> result = kakaoService.getUserInfo(kakaoToken);
        
        String snsId = (String) result.get("id");
        String userName = (String) result.get("nickname");
        String email = (String) result.get("email");
        
        account=accountService.findByAccount(email);
        if ( !account.getEmail().equals(email)) {
            account.setEmail(email);
            account.setPw(passwordEncoder.encode(userName));
            account.setUserName(userName);
            account.setEmail(email);
            account.setPrivilege("member");
            
            accountService.register(account);
        }
//        account= accountService.findByAccount(email);
        
        List<GrantedAuthority> roles = (List<GrantedAuthority>) account.getAuthorities();
        Authentication auth = new UsernamePasswordAuthenticationToken(account, null, roles);
        SecurityContextHolder.getContext().setAuthentication(auth);

        /* 로그아웃 처리 시, 사용할 토큰 값 */
        session.setAttribute("kakaoToken", kakaoToken);
        ModelAndView mv= new ModelAndView();
        mv.setViewName("redirect:/");
        return mv;

	}
	
	  

}

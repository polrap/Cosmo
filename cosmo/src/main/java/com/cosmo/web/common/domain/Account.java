package com.cosmo.web.common.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.cosmo.web.api.VO.MemberVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Repository
@Data
@NoArgsConstructor
public class Account implements UserDetails{

	private String userSeq;
	private String email;
	private String userName;
	private String pw;
	private String confirmPw;
	private String phoneNum;
	private String insertTime;
	private String updateTime;
	private String privilege;
	
	public Account(MemberVO memberVO) {
		this.userName=memberVO.getUserName();
		this.email=memberVO.getEmail();
		this.pw=memberVO.getPw();
		this.phoneNum=memberVO.getPhoneNum();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(this.privilege));
		return authList;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return pw;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

package com.cosmo.web.api.VO;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
@Data
public class MemberVO {
	
	private String userSeq;
	private String email;
	private String userName;
	private String pw;
	private String confirmPw;
	private String phoneNum;
	private String privilege;
	
}

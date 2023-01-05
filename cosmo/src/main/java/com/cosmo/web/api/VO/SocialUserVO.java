package com.cosmo.web.api.VO;

import org.springframework.stereotype.Repository;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Repository
@NoArgsConstructor
public class SocialUserVO {
	
	private String email;
	
	public SocialUserVO(String email) {
		this.email=email;
	}
}

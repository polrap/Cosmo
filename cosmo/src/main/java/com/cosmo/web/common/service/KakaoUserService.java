//package com.cosmo.web.common.service;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import com.cosmo.web.api.VO.SocialUserVO;
//import com.cosmo.web.common.domain.Account;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Service
//public class KakaoUserService {
//	@Autowired
//	private  PasswordEncoder passwordEncoder;
//	@Autowired
//	private Account account;
//	
//	  public SocialUserVO kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException {
//	        // 1. "인가 코드"로 "액세스 토큰" 요청
//	        String accessToken = getAccessToken(code);
//
//	        // 2. 토큰으로 카카오 API 호출
//	        SocialUserVO kakaoUserInfo = getKakaoUserInfo(accessToken);
//
//	        // 3. 카카오ID로 회원가입 처리
//	        User kakaoUser = registerKakaoUserIfNeed(kakaoUserInfo);
//
//	        // 4. 강제 로그인 처리
//	        Authentication authentication = forceLogin(kakaoUser);
//
//	        // 5. response Header에 JWT 토큰 추가
//	        kakaoUsersAuthorizationInput(authentication, response);
//	        return kakaoUserInfo;
//	    }
//	  
//	  	private String getAccessToken(String code) throws JsonProcessingException {
//	        // HTTP Header 생성
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//	        // HTTP Body 생성
//	        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//	        body.add("grant_type", "authorization_code");
//	        body.add("client_id", CLIENT_ID);
//	        body.add("redirect_uri", "http://localhost:8080/user/kakao/callback");
//	        body.add("code", code);
//
//	        // HTTP 요청 보내기
//	        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
//	        RestTemplate rt = new RestTemplate();
//	        ResponseEntity<String> response = rt.exchange(
//	                "https://kauth.kakao.com/oauth/token",
//	                HttpMethod.POST,
//	                kakaoTokenRequest,
//	                String.class
//	        );
//
//	        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
//	        String responseBody = response.getBody();
//	        ObjectMapper objectMapper = new ObjectMapper();
//	        JsonNode jsonNode = objectMapper.readTree(responseBody);
//	        return jsonNode.get("access_token").asText();
//	    }
//}

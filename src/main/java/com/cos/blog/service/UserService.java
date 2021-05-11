package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.model.kakaoToken;
import com.cos.blog.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

// 스프링이 컴포넌트 스캔을 통해 Bean에 등록 IoC 해준다
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encode;

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); // 1234
        String encPassword = encode.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
    }

    @Transactional
    public void 회원수정(User user) {
        // update에는 영속성 컨텍스트 User Object를 영속화시키고 영속화된 Object를 수정
        // SELECT를 해서 User Object를 DB로부터 가져오는 이유는 영속화를 위해서
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌
        User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("회원 찾기 실패");
        });

        String raawPassword = user.getPassword();
        String encPassword = encode.encode(raawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());

        // 회원수정 함수 종료시 = 서비스 종료 = transaction 종료 = commit 이 auto execute
        // 영속화된 persistance 객체의 변화가 감지되면(더티체킹) update문을 자동으로 execute
    }

    public String kakaoTokenLogin(String code){

            // POST 방식으로 key=value 방식으로 데이터를 카카오로 요청
            RestTemplate rt = new RestTemplate();

            // Http Header object create.
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

            // http Body object create.
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", "0e2b9006a61e302d21fb671112d39a83");
            params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
            params.add("code", code);

            // HttpHeader와 HttpBody Object를 하나의 object에 담기
            HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, header);

            // Http post 방식으로 요청 후 response 변수가 응답받음
            ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
            );

        // GSON, Json Simple, ObjectMapper

        ObjectMapper objectMapper = new ObjectMapper();
        kakaoToken token = null;

        try {
            token = objectMapper.readValue(response.getBody(), kakaoToken.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "" + response;

    }

    public String getKakaoInfo(String code){

        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization", "Bearer ");

        HttpEntity<MultiValueMap<String, String>> infoRequest = new HttpEntity<>(headers);

        ResponseEntity<String> response = rt.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.POST,
            infoRequest,
            String.class
        );

        return "" + response;
    }

    // @Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
    // public User 로그인(User user) {
    // return userRepository.findByUsernameAndPassword(user.getUsername(),
    // user.getPassword());
    // }

}

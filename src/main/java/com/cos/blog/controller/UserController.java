package com.cos.blog.controller;

import com.cos.blog.model.kakaoToken;
import com.cos.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/ 허용
// 그냥 주소가 /이면 index.jsp 허용
// static 이하에 있는 js, css image

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/kakao/callback")
    // data를 return 해주는 컨트롤러 함수
    // code 라는 피라미터에 매핑된 query String과 동일한 값을 담아준다.
    public String kakaoCallback(String code) {
        kakaoToken token = userService.kakaoTokenLogin(code);
        String resp = userService.getKakaoInfo(token);
        return "redirect:/";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

}

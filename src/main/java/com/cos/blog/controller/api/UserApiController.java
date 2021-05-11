package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import com.cos.blog.config.auth.PrincipalDetailService;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private PrincipalDetailService principalDetailService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController : save");
        user.setRole(RoleType.USER);
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user, HttpSession session) {
        userService.회원수정(user);
        // session registry
        UserDetails userDetail = principalDetailService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}

// @Autowired
// private HttpSession session;
// HttpSession은 Spring에 bean으로 등록되었기에
// autowired를 통해 객체를 받아와 아래 메소드의 인자를 선언하지않고 사용할 수 있다.
// 기본적인 로그인 방식(Spring 방식과 다름)

// @PostMapping("/api/user/login")
// public ResponseDto<Integer> login(@RequestBody User user, HttpSession
// session) {
// System.out.println("UserApiController : login 호출됨");
// User principal = userService.로그인(user); // principal(접근주체)

// if (principal != null) {
// session.setAttribute("principal", principal);
// }
// return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
// }
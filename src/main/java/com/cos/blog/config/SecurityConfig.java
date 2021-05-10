package com.cos.blog.config;

import com.cos.blog.config.auth.PrincipalDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@EnableWebSecurity // security filter 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 인증 체크

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean // IoC 컨테이너에 객체가 저장됨
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    // security가 대신 로그인하기위해 password를 intercept 하는데
    // 해당 password가 어떤 hash로 회원가입 되었는지 알아야
    // 같은 hash로 암호화해서 DB에 있는 hash와 compare 가능

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // security에서 hash화 된 password와 encodePWD() hash를 비교하여 password 동일한지 compare
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf token 비활성화(테스트시 걸어두는게 좋음)
        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**").permitAll().anyRequest()
                .authenticated().and().formLogin().loginPage("/auth/loginForm").loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/");
        // spring security가 요청을 intercept하여 처리
    }

}
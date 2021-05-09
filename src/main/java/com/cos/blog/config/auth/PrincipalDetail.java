package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import com.cos.blog.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// spring security가 로그인 요청을 intercept하여 로그인 진행
// 완료되면 Userdetails 타입의 오브젝트를
// spring security 고유 세션저장소에 저장
public class PrincipalDetail implements UserDetails {

    private User user; // composition

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // account가 만료되지 않았는지 return (true : 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // account가 잠겨있지 않는지 return (true : 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되지 않았는지 return (true : 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // account 활성화상태 인지 return (true : 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collections = new ArrayList<>();
        collections.add(() -> {
            return "Role_" + user.getRole();
        });
        // collections.add(new GrantedAuthority() {
        // @Override
        // public String getAuthority() {
        // return "Role_" + user.getRole();
        // }
        // });

        return collections;
    }

}

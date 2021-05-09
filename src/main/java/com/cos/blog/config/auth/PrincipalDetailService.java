package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// bean 등록
@Service
public class PrincipalDetailService implements UserDetailsService {

    // spring이 login request를 intercept하여 username, password
    // info를 받아오는데 password 부분처리는 알아서 처리함
    // 해당 username이 DB에 있는지만 확인해주면 됨.
    // 아래 method가 DB에 username이 있는지 확인

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. :" + username);
        });
        // security session에 user info가 save됨
        return new PrincipalDetail(principal);
    }

}

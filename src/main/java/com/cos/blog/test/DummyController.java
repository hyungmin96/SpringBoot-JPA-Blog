package com.cos.blog.test;

import com.cos.blog.repository.UserRepository;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// html파일이 아니라 data를 return 해주는 controller = RestController
@RestController
public class DummyController {

    // 의존성 주입 (DI)
    @Autowired
    private UserRepository repository;

    @GetMapping("/dummy/users")
    public List<User> list() {
        return repository.findAll();
    }

    // 한 페이지당 2건에 데이터를 return
    @GetMapping("/dummy/user")
    public List<User> pageList(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = repository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delte(@PathVariable int id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다.";
        }
        return "삭제되었습니다.";
    }

    // save 함수는 id를 전달하지 않으면 insert를 해주고
    // save 함수는 id를 전달하면 id에 대한 데이터가 있으면 update, 아닐경우 insert
    // email, password를 받아와야함
    // save를 통해 update를 하면 누락되는 값은 null로 처리되기에 잘 사용하지 않음
    @Transactional // Transaction 어노테이션이 있으면 save 메소드 없이 저장이 가능하다.
    // 트랜잭션은 해당 메소드가 종료될때 자동으로 업데이트된 값을 commit한다.
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        System.out.println("id " + id);
        System.out.println("password " + requestUser.getPassword());
        System.out.println("email " + requestUser.getEmail());

        User user = repository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // repository.save(user);

        // 더티 체킹
        return user;
    }

    // {id} 주소로 파라미터를 전달 받을 수 있음
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // findById 는 optional 형식의 return을 받는다.
        // user/4를 찾는데 데이터베이스에 존재하지 않다면 user가 null이 되므로
        // null이 return되면 오류가 발생할 수 있으니 optional로 user객체가 null이 아니면 return
        User user = repository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다.");
            }
        });
        // 요청 : 웹 브라우저에서 진행
        // user 객체는 java obj
        // java obj => 웹브라우저가 이해할 수 있는 데이터로 변환 => json
        // springBoot = messageConverter가 응답시에 자동 작성
        // java obj를 return 하게 된다면 messageConverter가 Jackson 라이브러리 호출
        // user객체를 json형식으로 웹브라우저에 return 하게 된다.
        return user;
    }

    // http의 body에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    // requestparams 없이 key=value의 value값을 아래의 변수순서대로 입력받게 된다.
    public String join(User user) {
        System.out.println("username " + user.getUsername());
        System.out.println("password " + user.getPassword());
        System.out.println("email " + user.getEmail());

        user.setRole(RoleType.USER);
        repository.save(user);

        return "회원가입이 완료되었습니다.";
    }

}

package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 => 응답(Data)
// 사용자가  요청 => 응답(Html 파일) @Controller

@RestController
public class HttpController {

    // 인터넷 브라우저에서는 GET 메소드만 확인이 가능

    // public String getTest(@RequestParam int id, @RequestParam String username) {
    // url에서 각각의 requestpara의 값을 요청받을때 사용

    // ?id=1&username=hyungmin&password=4564&email=ssar@nate.com
    // /htt/get 뒤의 ? 뒤의 url은 member 객체에 담긴 변수와 pair를 구성하여 각 정보를 매핑해줌
    @GetMapping("/http/get")
    public String getTest(Member m) {
        return "get 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) { // MessageConver가 입력받는 json값을 member 객체의 변수에 맞게 pair 하여 출력
        return "POST 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    @PutMapping("/http/put")
    public String putTest() {
        return "put 요청";
    }

    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }

}

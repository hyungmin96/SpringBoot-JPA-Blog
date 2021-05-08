package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// User Object의 기본 키 타입은 Integer라는 것을 명시
// DAO
// 자동으로 bean 등록이 된다.
// @Repository 생략됨
public interface UserRepository extends JpaRepository<User, Integer> {

}

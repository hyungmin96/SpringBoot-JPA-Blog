package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM => JAVA(다른언어) Object => 테이블이름으로 매핑해줌

@Entity // User 클래스가 MySQL에 자동으로 테이블이 생성됨
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
// @DynamicInsert insert시에 null인 값을 빼준다.
public class User {

    @Id // primary key
    // 프로젝트에서 연결된 Db의 넘버링을 따라간다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 시퀸스, auto_increment

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // DB는 RoleType이 없다.
    // @ColumnDefault("user")
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰는게 좋다.

    @CreationTimestamp
    private Timestamp createDate;

}

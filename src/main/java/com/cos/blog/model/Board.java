package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 <html>이 섞여서 디자인 됨

    private int count;

    @ManyToOne // Many = Board, User = One
    @JoinColumn(name = "userId")
    private User user; // Db는 오브젝트를 저장할 수 없다. Fk, 자바는 오브젝트를 저장할 수 있다.

    // mappedBy 연관관계의 주인이 아니다 (난 Fk가 아님) Db에 칼럼생성 X
    // mappedBy 는 불러올 객체의 변수명을 적어줘야함
    // @JoinColumn(name = "replyId")
    // reply는 1개의 게시글에 여러개가 등록될 수 있으니 Column을 추가하면 안됨
    
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "board")
    private List<Reply> replys;

    @CreationTimestamp
    private Timestamp createDate;

}

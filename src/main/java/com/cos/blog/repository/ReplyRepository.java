package com.cos.blog.repository;

import javax.transaction.Transactional;

import com.cos.blog.model.Reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
    
    @Modifying // native query 작업을 수행할때 필요한 annotaion(데이터변경이 발생하는 insert, delete, update에 사용)
    // jap 영속성컨텍스트에 1차 캐싱에 엔티티가 존재하면 db에 접근하지 않으므로 예측값이 반영되지 않을수있으니 주의
    // 이 쿼리를 사용해야한다면 영속성 컨텍스트를 clear해주어 1차캐싱을 비워 db에 접근할수있도록 설정해야함
    @Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
    int mSave(int userId, int boardId, String content); //jdbc가 update된 행의 개수를 return 

}

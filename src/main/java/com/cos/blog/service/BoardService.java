package com.cos.blog.service;

import java.util.List;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해 Bean에 등록 IoC 해준다
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    public List<Board> 글목록() {
        return boardRepository.findAll();
    }

    // @Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
    // public User 로그인(User user) {
    // return userRepository.findByUsernameAndPassword(user.getUsername(),
    // user.getPassword());
    // }

}

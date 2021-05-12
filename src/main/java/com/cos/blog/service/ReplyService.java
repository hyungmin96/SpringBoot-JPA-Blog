package com.cos.blog.service;

import javax.transaction.Transactional;

import com.cos.blog.dto.replyDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

import org.dom4j.IllegalAddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository usesrRepository;

    @Transactional
    public void 덧글작성(replyDto replyRequestDto){

        Board board = boardRepository.findById(replyRequestDto.getBoardId()).orElseThrow(() ->{
            return new IllegalAddException("댓글쓰기 실패 : 게시글 id 검색실패");
        });

        User user = usesrRepository.findById(replyRequestDto.getUserId()).orElseThrow(() ->{
            return new IllegalAddException("사용자 검색 실패");
        });

        String content = replyRequestDto.getContent();

        Reply reply = Reply.builder().user(user).board(board).content(content).build();

        replyRepository.save(reply);
    }

}

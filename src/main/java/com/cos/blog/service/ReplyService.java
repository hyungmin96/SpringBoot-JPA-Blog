package com.cos.blog.service;

import javax.transaction.Transactional;

import com.cos.blog.dto.replyDto;
import com.cos.blog.dto.replyInfoDto;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    
    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void 덧글작성(replyDto replyRequestDto){

        // Board board = boardRepository.findById(replyRequestDto.getBoardId()).orElseThrow(() ->{
        //     return new IllegalAddException("댓글쓰기 실패 : 게시글 id 검색실패");
        // });

        // User user = usesrRepository.findById(replyRequestDto.getUserId()).orElseThrow(() ->{
        //     return new IllegalAddException("사용자 검색 실패");
        // });

        // String content = replyRequestDto.getContent();

        // Reply reply = Reply.builder().user(user).board(board).content(content).build();

        // replyRepository.save(reply);

        replyRepository.mSave(replyRequestDto.getUserId(), replyRequestDto.getBoardId(), replyRequestDto.getContent());

    }

    @Transactional
    public void 덧글삭제(replyInfoDto infoDto){
        replyRepository.deleteById(infoDto.getReplyid());
    }

}

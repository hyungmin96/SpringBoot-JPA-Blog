package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.dto.replyDto;
import com.cos.blog.dto.replyInfoDto;
import com.cos.blog.service.ReplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class replyApiController {
    
    @Autowired
    private ReplyService replyService;

    @PostMapping("/api/board/{boardId}/comment")
    public ResponseDto<Integer> 덧글작성(@RequestBody replyDto replyRequestDto){
        replyService.덧글작성(replyRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/comment/delete")
    public ResponseDto<Integer> 덧글삭제(@RequestBody replyInfoDto InfoDto){
        replyService.덧글삭제(InfoDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}

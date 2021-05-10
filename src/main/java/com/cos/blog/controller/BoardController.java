package com.cos.blog.controller;

import com.cos.blog.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({ "", "/" })
    public String index(Model model) {
        model.addAttribute("boards", boardService.글목록());
        return "index";
        // viewResolver가 작동
        // index 페이지로 model 정보를 들고 이동하게됨
    }

    // user 권한 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

}

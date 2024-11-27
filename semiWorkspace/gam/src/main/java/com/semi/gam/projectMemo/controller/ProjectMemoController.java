package com.semi.gam.projectMemo.controller;

import com.semi.gam.projectMemo.service.ProjectMemoService;
import com.semi.gam.projectMemo.vo.ProjectMemoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("projectMemo")
@RequiredArgsConstructor
public class ProjectMemoController {

    private final ProjectMemoService service;
    
    //메모 작성
    @GetMapping("write")
    public void write(){
    }

    @PostMapping("write")
    public String write(ProjectMemoVo vo){
        int result = service.write(vo);
        if(result != 1){
            return "redirect:/error";
        }
        return "redirect:/projectMemo/list";

    }

    //메모 수정

    //메모 목록조회(카드)
    @GetMapping("cardList")
    public void cardList(){

    }


    //메모 목록조회(리스트)
    @GetMapping("list")
    public void list(){
    }

    //메모 상세조회
    @GetMapping("detail")
    public String detail(){
        return "projectMemo/detail";
    }

    //메모 삭제

    //


}

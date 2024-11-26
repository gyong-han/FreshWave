package com.semi.gam.project.controller;

import com.semi.gam.project.service.ProjectService;
import com.semi.gam.project.vo.ProjectVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("project")
@RequiredArgsConstructor
public class ProjectController {

        public final ProjectService service;


    //프로젝트 작성
    @GetMapping("write")
    public String write(){
        return "/project/write";
    }

    @PostMapping("write")
    public String write(ProjectVo vo){
        int result = service.write(vo);
        if(result != 1){
            return "redirect:/error";
        }
        return "redirect:/project/cardList";


    }

    @GetMapping("cardList")
    //프로젝트 목록(카드)
    public String cardList(){
        return "/project/cardList";
    }


    @GetMapping("list")
    //프로젝트 목록 (리스트)
    public void list(){
    }

    @GetMapping("selectOne")
    //프로젝트 상세조회
    public String selectOne(){
        return "/project/selectOne";
    }

    @GetMapping("edit")
    //프로젝트 수정
    public String edit(){
        return "/project/edit";
    }

    @GetMapping("delete")
    //프로젝트 삭제
    public String delete(){
        return "/project/delete";
    }

    @GetMapping("select")
    //프로젝트 검색
    public String select(){
        return "/project/select";
    }

    @GetMapping("addMember")
    //프로젝트 인원추가
    public String addMember(){
        return "/project/addMember";
    }





}

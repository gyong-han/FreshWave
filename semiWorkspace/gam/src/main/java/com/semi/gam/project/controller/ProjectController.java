package com.semi.gam.project.controller;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.service.ProjectService;
import com.semi.gam.project.vo.ProjectVo;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("detail")
    //프로젝트 상세조회
    public String selectOne(){
        return "/project/detail";
    }

    @GetMapping("edit")
    //프로젝트 수정
    public String edit(){
        return "/project/edit";
    }

    @PostMapping("edit")
    public String edit(ProjectVo vo, HttpSession session, MemberVo memberVo){
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        memberVo.setEmpNo(loginMemberVo.getNo());
        int result = service.edit(vo, memberVo);
        if(result < 1){
            return "redirect:/error";
        }
        return "redirect:/project/detail";
    }

    @GetMapping("delete")
    //프로젝트 삭제
    public String delete(ProjectVo vo){
        service.delete(vo);
        return "redirect:/board/list";
    }
}

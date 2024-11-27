package com.semi.gam.project.controller;

import com.semi.gam.member.service.MemberService;
import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.service.ProjectService;
import com.semi.gam.project.vo.ProjectVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("project")
@RequiredArgsConstructor
public class ProjectController {

        public final ProjectService service;
        public final MemberService memberService;


    //프로젝트 작성
    @GetMapping("write")
    public String write(Model model , HttpSession session){
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        List<MemberVo> empVoList = memberService.getEmpVo();

        model.addAttribute("empVoList" , empVoList);
        return "project/write";
    }

    @PostMapping("write")
    public String write(ProjectVo vo , MemberVo memberVo, HttpSession session){
        MemberVo loginMemberVo1 = memberVo; //TODO 로그인정보 만들어지면 지우기
        session.setAttribute("loginMemberVo" , loginMemberVo1);//TODO 로그인정보 만들어지면 지우기
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        loginMemberVo.setEmpNo("1");        //TODO 로그인정보 만들어지면 지우기
        memberVo.setEmpNo(loginMemberVo.getEmpNo());
        int result = service.write(vo, memberVo);
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
    public String list(){

        //ProjectVo vo, MemberVo mvo , Employee evo

        return "project/list";
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

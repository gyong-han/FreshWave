package com.semi.gam.project.controller;

import com.semi.gam.dept.service.DeptService;
import com.semi.gam.dept.vo.DeptVo;
import com.semi.gam.member.service.MemberService;
import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.service.ProjectService;
import com.semi.gam.project.vo.ProjectMemberVo;
import com.semi.gam.project.vo.ProjectVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("project")
@RequiredArgsConstructor
public class ProjectController {

        public final ProjectService service;
        public final MemberService memberService;
//        public final DeptService deptService;


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
        loginMemberVo.setId("3");        //TODO 로그인정보 만들어지면 지우기
        memberVo.setId(loginMemberVo.getId());
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
    public String list(Model model, HttpSession session){
        MemberVo loginMemberVo1 = new MemberVo(); //TODO 로그인정보 만들어지면 지우기
        session.setAttribute("loginMemberVo" , loginMemberVo1);//TODO 로그인정보 만들어지면 지우기
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        loginMemberVo.setId("1"); //TODO 로그인정보 만들어지면 지우기

        List<ProjectVo> projectVoList = service.getProjectList(loginMemberVo);
        List<ProjectVo> projectVoList1 = service.getProjectAddMemberVo(loginMemberVo);

        model.addAttribute("projectVoList" , projectVoList);
        model.addAttribute("projectVoList1" , projectVoList1);
        return "project/list";
    }

    @GetMapping("detail")
    //프로젝트 상세조회
    public String selectOne(Model model,@RequestParam("projectNo") String key, HttpSession session){
        MemberVo loginMemberVo1 = new MemberVo(); //TODO 로그인정보 만들어지면 지우기
        session.setAttribute("loginMemberVo" , loginMemberVo1);//TODO 로그인정보 만들어지면 지우기
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        loginMemberVo.setId("1"); //TODO 로그인정보 만들어지면 지우기
        ProjectVo projectVo = service.getProject(key,loginMemberVo);
        model.addAttribute("projectVo" , projectVo);

        ProjectMemberVo vo = service.getAddMember(key, loginMemberVo);

        model.addAttribute("add" , vo);
        System.out.println("vo = " + vo);
        System.out.println("model = " + model);
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
        memberVo.setId(loginMemberVo.getNo());
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

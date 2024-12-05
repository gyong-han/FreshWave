package com.semi.gam.projectMemo.controller;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.projectMemo.service.ProjectMemoService;
import com.semi.gam.projectMemo.vo.ProjectMemoVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("projectMemo")
@RequiredArgsConstructor
public class ProjectMemoController {

    private final ProjectMemoService service;

    //메모 작성
    @GetMapping("write")
    public void write(@RequestParam("projectNo") String key, Model model){

        model.addAttribute("key" , key );

    }

    @PostMapping("write")
    public String write(ProjectMemoVo vo, HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        int result = service.write(vo, loginMemberVo);
        if(result != 1){
            return "redirect:/error";
        }
        return "redirect:/projectMemo/list?projectNo=" + vo.getPrjKey();

    }

    //메모 수정
    @GetMapping("edit")
    public void edit(ProjectMemoVo vo, Model model){
        String key = vo.getPrjKey();

        model.addAttribute("vo" , vo);
        model.addAttribute("key" , key);
    }

    @PostMapping("edit")
    public String edit(ProjectMemoVo vo){
        System.out.println("vo = " + vo);
        int reulst = service.edit(vo);

        return "redirect:/projectMemo/detail?projectNo="+vo.getPrjKey();
    }

    //메모 목록조회(카드)
    @GetMapping("cardList")
    public void cardList(){

    }


    //메모 목록조회(리스트)
    @GetMapping("list")
    public String list(@RequestParam("projectNo") String key, Model model){
        model.addAttribute("key", key);
        return "projectMemo/list";
    }

//     메모 목록조회(데이터)
    @PostMapping("list")
    @ResponseBody
    public List<ProjectMemoVo> list(@RequestParam("projectNo") String key){
        List<ProjectMemoVo> memoList = service.list(key);
        return memoList ;
    }

    //메모 상세조회
    @GetMapping("detail")
    public String detail(String no, Model model){
       ProjectMemoVo memoVo =  service.getMemoVo(no);
       model.addAttribute("memoVo", memoVo);
       model.addAttribute("no" , no);
       return "projectMemo/detail";
    }

    //메모 삭제
    @GetMapping("delete")
    public void delete(@RequestParam("projectNo") String no){


    }



}

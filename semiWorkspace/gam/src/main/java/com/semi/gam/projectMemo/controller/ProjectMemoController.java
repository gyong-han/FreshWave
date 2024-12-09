package com.semi.gam.projectMemo.controller;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.vo.PageVo;
import com.semi.gam.projectMemo.service.ProjectMemoService;
import com.semi.gam.projectMemo.vo.ProjectMemoVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("projectMemo")
@RequiredArgsConstructor
public class ProjectMemoController {

    private final ProjectMemoService service;

    //메모 작성
    @GetMapping("write")
    public String write(@RequestParam("projectNo") String key, Model model, HttpSession session){
        MemberVo loginMemberVo  = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
        model.addAttribute("key" , key );
        return "projectMemo/write";
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
    public String edit(ProjectMemoVo vo, Model model, HttpSession session){
        MemberVo loginMemberVo  = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }

        String key = vo.getPrjKey();

        model.addAttribute("vo" , vo);
        model.addAttribute("key" , key);

        return "projectMemo/edit";
    }

    @PostMapping("edit")
    public String edit(ProjectMemoVo vo){
        int reulst = service.edit(vo);
        String prjkey = vo.getPrjKey();
        String no = vo.getNo();
        return "redirect:/projectMemo/detail?projectNo="+prjkey+"&no="+no;
    }

    //메모 목록조회(카드)
    @GetMapping("cardList")
    public String cardList(@RequestParam("projectNo") String key, Model model, HttpSession session){
        MemberVo loginMemberVo  = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
        model.addAttribute("key", key);
        return "projectMemo/cardList";
    }
    //메모 목록조회(카드 데이터)
    @GetMapping("cardListData")
    @ResponseBody
    public HashMap cardListData(HttpSession session, String key, Model model){
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        ProjectMemoVo prjName = service.getProjectName(key);
        List<ProjectMemoVo> waitList = service.getProjectMemoWaitList(loginMemberVo, key);
        List<ProjectMemoVo> ingList = service.getProjectMemoIngList(loginMemberVo, key);
        List<ProjectMemoVo> complateList = service.getProjectMemoComplateList(loginMemberVo, key);
        HashMap map = new HashMap();
        map.put("name", prjName);
        map.put("wait" , waitList);
        map.put("ing" , ingList);
        map.put("com" , complateList);
        model.addAttribute("key" , key);

        return map;
    }

    //메모 목록조회(리스트)
    @GetMapping("list")
    public String list(@RequestParam("projectNo") String key, Model model, HttpSession session){
        MemberVo loginMemberVo  = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
        model.addAttribute("key", key);
        return "projectMemo/list";
    }

//     메모 목록조회(데이터)
    @PostMapping("list")
    @ResponseBody
    public HashMap list(HttpSession session, @RequestParam("projectNo") String key, @RequestParam(name="pno" , defaultValue = "1", required = false) int currentPage){
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        int listCount = service.getProjectMemoListCnt(loginMemberVo, key);
        int pageLimit = 5;
        int projectLimit = 8;
        PageVo pvo = new PageVo(currentPage, listCount, pageLimit, projectLimit);
        System.out.println("pvo = " + pvo);
        List<ProjectMemoVo> memoList = service.list(key, pvo);
        HashMap map = new HashMap();

        map.put("memoList" , memoList);
        map.put("pvo" , pvo);

        return map;
    }

    //메모 상세조회
    @GetMapping("detail")
    public String detail(String no, Model model, HttpSession session){
        MemberVo loginMemberVo  = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
       ProjectMemoVo memoVo =  service.getMemoVo(no);
       model.addAttribute("memoVo", memoVo);
       model.addAttribute("no" , no);
       return "projectMemo/detail";
    }

    //메모 삭제
    @GetMapping("delete")
    public String delete(String no, @RequestParam("projectNo") String projectNo){
        int result = service.delete(no);

        if(result != 1){
            throw new IllegalStateException();
        }
        return "redirect:/projectMemo/list?projectNo=" + projectNo;

    }



}

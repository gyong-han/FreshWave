package com.semi.gam.member.controller;

import com.semi.gam.admin.vo.AdminVo;
import com.semi.gam.dept.vo.DeptVo;
import com.semi.gam.employee.vo.EmployeeVo;
import com.semi.gam.job.vo.JobVo;
import com.semi.gam.member.service.MemberService;
import com.semi.gam.member.vo.MemberVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
@Slf4j
public class MemberController {

    private final MemberService service;

    //직급 목록
    @GetMapping("job")
    @ResponseBody
    public List<JobVo> job(){
        return service.getJobVoList();
    }

    //부서 목록
    @GetMapping("dept")
    @ResponseBody
    public List<DeptVo> dept(){
        return service.getDeptVoList();
    }

    //로그인(화면)
    @GetMapping("login")
    public String login(){
        return "member/login";
    }

    //로그인(데이터 처리)
    @PostMapping("login")
    public String login(MemberVo mvo, AdminVo avo, HttpSession session){
       if(avo.getId().equals("ADMIN")){
           AdminVo loginAdminVo = service.loginAdmin(avo);
           if(loginAdminVo == null){
               throw new IllegalStateException("error-admin");
           }
           return "redirect:/admin/home";

       }
        MemberVo loginMemberVo = service.loginMember(mvo);
        if(loginMemberVo == null){
            throw new IllegalStateException("error-member");
        }
        return "redirect:/home";

    }

    //회원추가 화면 (관리자만 사용)
    @GetMapping("join")
    public String join(Model model){
        List<DeptVo> deptVoList = service.getDeptVoList();
        List<JobVo> jobVoList = service.getJobVoList();
        model.addAttribute("deptVoList",deptVoList);
        model.addAttribute("jobVoList",jobVoList);

        return "member/join";
    }

    //회원추가 데이처 처리
    @PostMapping("join")
    public String join(MemberVo mvo, EmployeeVo evo, HttpSession session){
        int result = service.join(mvo,evo);

        if(result < 0){
            throw new IllegalStateException("error-join");
        }
        session.setAttribute("alertMsg","회원추가 완료");
        return "redirect:/member/detail";

    }

}

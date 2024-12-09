package com.semi.gam.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semi.gam.admin.vo.AdminVo;
import com.semi.gam.dept.vo.DeptVo;
import com.semi.gam.employee.vo.EmployeeVo;
import com.semi.gam.job.vo.JobVo;
import com.semi.gam.member.service.MemberService;
import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.util.FileUploader;
import com.semi.gam.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
@Slf4j
public class MemberController{

    @Value("#{pathInfo.getMemberPath()}")
    private String profilePath;

    private final MemberService service;

    //직급 목록
    @GetMapping("job")
    @ResponseBody
    public List<JobVo> job(){return service.getJobVoList();}

    //부서 목록
    @GetMapping("dept")
    @ResponseBody
    public List<DeptVo> dept(){return service.getDeptVoList();}

    //로그인(화면)
    @GetMapping("login")
    public String login(){return "member/login";}

    //로그인(데이터 처리)
    @PostMapping("login")
    public String login(MemberVo mvo, AdminVo avo, EmployeeVo evo, HttpSession session){
       if(avo.getId().equals("ADMIN")){
           AdminVo loginAdminVo = service.loginAdmin(avo);
           if(loginAdminVo == null){
               throw new IllegalStateException("error-admin");
           }
           session.setAttribute("loginMemberVo",null);
           session.setAttribute("loginAdminVo",loginAdminVo);
           return "redirect:/admin/home";
       }
        MemberVo loginMemberVo = service.loginMember(mvo);
        int result = service.stratlogin(evo);
        if(loginMemberVo == null){
            throw new IllegalStateException("error-member");
        }
        session.setAttribute("loginAdminVo",null);
        session.setAttribute("loginMemberVo",loginMemberVo);
        System.out.println("loginMemberVo = " + loginMemberVo);
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
        return "redirect:/admin/list";

    }

    // 닉네임 중복체크
    @PostMapping("dup-nick")
    @ResponseBody
    public String checkDupNick(String nick) throws Exception {
        HashMap<String,String> m = new HashMap<>();
        m.put("data",nick);

        boolean isDup = service.checkDupNick(nick);
        if(isDup){
            m.put("status", "bad");
        }else{
            m.put("status","good");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(m);
        return str;
    }

    //마이페이지
    @GetMapping("mypage")
    public String mypage(HttpSession session){
        if(session.getAttribute("loginMemberVo") == null){
            return "redirect:/member/login";
        }
        return "member/mypage";
    }

    //마이페이지에서 회원 수정
    @PostMapping("edit")
    public String edit(MemberVo vo, HttpSession session, MultipartFile f) throws Exception {
        String changeName = "";
        vo.setProfile(changeName);
        if(f != null){
            changeName = FileUploader.save(f , profilePath);
            vo.setProfile(changeName);
        }

        MemberVo loginMemberVo = (MemberVo)session.getAttribute(("loginMemberVo"));
        MemberVo updateMember = service.edit(vo);
        updateMember.setId(loginMemberVo.getId());


        session.setAttribute("loginMemberVo",updateMember);

        if(updateMember == null){
            throw new IllegalStateException("ERROR-MYPAGE-EDIT");
        }
        return "redirect:/member/mypage";
    }

    //로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute(("loginMemberVo"));
        session.setAttribute("loginMemberVo",null);

        return "redirect:/member/login";
    }

    //퇴근버튼 누르고 로그아웃
    @GetMapping("logOutFinish")
    @ResponseBody
    public int finishLogOut(HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute(("loginMemberVo"));
        System.out.println("loginMemberVo = " + loginMemberVo);
        int result = service.logOutFinish(loginMemberVo);
        if(result != 1){
            throw new IllegalStateException("ERROR-MEMBER-logOutFinish");
        }
        session.setAttribute("loginMemberVo",null);

        return result;

    }

    //회원이 회원 목록조회
    @GetMapping("list")
    public String list(Model model){
        List<DeptVo> deptVoList = service.getDeptVoList();
        model.addAttribute("deptVoList" , deptVoList);
        return "member/list";
    }


    @GetMapping("list/data")
    @ResponseBody
    public HashMap list(@RequestParam(name="pno", required = false, defaultValue = "1")int currentPage ,
                        String searchType , String searchValue){
        int listCount = service.getMemberCnt(searchType,searchValue);
        int pageLimit = 5;
        int boardLimit = 10;
        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
        List<EmployeeVo> employeeVoList = service.getMemberList(pvo,searchType,searchValue);

        HashMap map = new HashMap();
        map.put("a" , employeeVoList);
        map.put("b" , pvo);
        return map;

    }


}

package com.semi.gam.admin.controller;

import com.semi.gam.admin.service.AdminService;
import com.semi.gam.admin.vo.AdminVo;
import com.semi.gam.board.service.BoardService;
import com.semi.gam.board.vo.BoardVo;
import com.semi.gam.dept.vo.DeptVo;
import com.semi.gam.employee.vo.EmployeeVo;
import com.semi.gam.job.vo.JobVo;
import com.semi.gam.member.service.MemberService;
import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("admin")
public class AdminController {

    private final AdminService service;
    private final BoardService bs;
    private final MemberService ms;
    //직급 목록
    @GetMapping("job")
    @ResponseBody
    public List<JobVo> job(){return ms.getJobVoList();}

    //부서 목록
    @GetMapping("dept")
    @ResponseBody
    public List<DeptVo> dept(){return ms.getDeptVoList();}

    @GetMapping("home")
    public String home(Model model,HttpSession session){
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        if(loginAdminVo == null){
            return "redirect:/member/login";
        }
        List<BoardVo> boardVoList = bs.getBoardHomeList();
        List<EmployeeVo> employeeVoList = service.getMemberHomeList();

        model.addAttribute("employeeVoList",employeeVoList);
        model.addAttribute("boardVoList",boardVoList);
        return "admin/home";
    }

    @GetMapping("list")
    public String list(Model model){
        List<DeptVo> deptVoList = ms.getDeptVoList();
        model.addAttribute("deptVoList" , deptVoList);
        return "admin/list";
    }


    //관리자가 회원 목록조회(화면)
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




    //관리자가 회원 수정(화면)
    @GetMapping("edit")
    public void edit(Model model,String no){
        EmployeeVo vo = service.getMember(no);
        List<DeptVo> deptVoList = ms.getDeptVoList();
        List<JobVo> jobVoList = ms.getJobVoList();
        model.addAttribute("vo",vo);
        model.addAttribute("deptVoList",deptVoList);
        model.addAttribute("jobVoList",jobVoList);
    }

    //관리자가 회원 수정(데이터)
    @PostMapping("edit")
    public String edit(EmployeeVo vo, HttpSession session) throws IOException {
        int result = service.edit(vo);
        System.out.println("vo = " + vo);
        System.out.println("result = " + result);
        if(result != 1){
            throw new IllegalStateException("ERROR-ADMIN-MEMBER-EDIT");
        }
        return "redirect:/admin/detail?no="+vo.getNo();


    }

    //관리자가 회원 상세조회(화면)
    @GetMapping("detail")
    public String detail(String no , Model model){
        EmployeeVo vo = service.getMember(no);
        model.addAttribute("vo" , vo);

        return "admin/detail";
    }

    //관리자가 회원 삭제(화면)
    @GetMapping("del")
    public String del(String no, HttpSession session){
        int result = service.del(no);
        if (result != 1){
            throw new IllegalStateException("ERROR-ADMIN-MEMBER-DEL");
        }
        return "redirect:/admin/list";
    }

    //탈퇴
    @GetMapping("listDel")
    public String listDel(Model model){
        List<DeptVo> deptVoList = ms.getDeptVoList();
        model.addAttribute("deptVoList" , deptVoList);
        return "admin/listDel";
    }


    //탈퇴한 회원 목록 조회
    @GetMapping("listDel/data")
    @ResponseBody
    public HashMap listDel(@RequestParam(name="pno", required = false, defaultValue = "1")int currentPage ,
                        String searchType , String searchValue){
        int listCount = service.getMemberCntDel(searchType,searchValue);
        int pageLimit = 5;
        int boardLimit = 10;
        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
        List<EmployeeVo> employeeVoList = service.getMemberListDel(pvo,searchType,searchValue);

        HashMap map = new HashMap();
        map.put("a" , employeeVoList);
        map.put("b" , pvo);
        return map;

    }

    //탈퇴한 회원 상세조회
    @GetMapping("detailDel")
    public String detailDel(String no , Model model){
        EmployeeVo vo = service.getMemberDel(no);
        model.addAttribute("vo" , vo);

        return "admin/detailDel";
    }

    //탈퇴한 회원 삭제
    @GetMapping("out")
    public String out(String no, HttpSession session){
        int result = service.out(no);
        if (result != 1){
            throw new IllegalStateException("ERROR-ADMIN-MEMBER-OUT");
        }
        return "redirect:/admin/listDel";
    }

    //로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session){
        AdminVo loginMemberVo = (AdminVo) session.getAttribute(("loginAdminVo"));
        session.setAttribute("loginAdminVo",null);

        return "redirect:/member/login";
    }

}

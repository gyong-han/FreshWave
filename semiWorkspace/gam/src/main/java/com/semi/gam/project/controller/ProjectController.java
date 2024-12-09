package com.semi.gam.project.controller;


import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.service.ProjectService;
import com.semi.gam.project.vo.PageVo;
import com.semi.gam.project.vo.ProjectMemberVo;
import com.semi.gam.project.vo.ProjectVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("project")
@RequiredArgsConstructor
public class ProjectController {

        public final ProjectService service;


    //프로젝트 작성
    @GetMapping("write")
    public String write(Model model , HttpSession session){
        MemberVo loginMemberVo  = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
        List<ProjectMemberVo> empVoList = service.getAddMemberVo();
        model.addAttribute("empVoList" , empVoList);
        return "project/write";
    }

    @PostMapping("write")
    public String write(ProjectVo vo , MemberVo memberVo, HttpSession session, ProjectMemberVo projectMemberVo){
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        memberVo.setId(loginMemberVo.getId());
        int result = service.write(vo, memberVo);
        String prjKey = service.getProjectKey(vo, loginMemberVo);
        int result2 = service.setAddMember(projectMemberVo, prjKey);
        if(result != 1){
            return "redirect:/error";
        }
        return "redirect:/project/list";
    }

    @GetMapping("cardList")
    //프로젝트 목록(카드)
    public String cardList(HttpSession session){
        MemberVo loginMemberVo  = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
        return "/project/cardList";
    }

    @GetMapping("cardListData")
    @ResponseBody
    public List<ProjectVo> cardListData(HttpSession session){
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        return service.getProjectCardList(loginMemberVo);
    }

    @GetMapping("list")
    //프로젝트 목록 (리스트)
    public String list(Model model, HttpSession session, @RequestParam(name="pno" , defaultValue = "1", required = false) int currentPage){
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
        int listCount = service.getProjectListCnt(loginMemberVo);
        int pageLimit = 5;
        int projectLimit = 8;
        PageVo pvo = new PageVo(currentPage, listCount, pageLimit, projectLimit);
        List<ProjectVo> projectVoList = service.getProjectList(loginMemberVo, pvo);
        model.addAttribute("projectVoList" , projectVoList);
        model.addAttribute("pvo" , pvo);

        return "project/list";
    }

    @GetMapping("detail")
    //프로젝트 상세조회
    public String selectOne(Model model,@RequestParam("projectNo") String key, HttpSession session){
        MemberVo loginMemberVo  = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
        ProjectVo projectVo = service.getProject(key,loginMemberVo);
        model.addAttribute("projectVo" , projectVo);
        List<ProjectMemberVo> vo = service.getAddMember(key);
        model.addAttribute("add" , vo);

        return "project/detail";
    }

    @GetMapping("edit")
    //프로젝트 수정
    public String edit(Model model, @RequestParam("projectNo") String key, HttpSession session){
        //로그인 정보 가져오기
        MemberVo loginMemberVo  = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }

        //화면 정보 가져오기
        List<ProjectMemberVo> empVoList = service.getAddMemberVo();
        ProjectVo projectVo = service.getProject(key,loginMemberVo);
        List<ProjectMemberVo> vo = service.getAddMember(key);
        model.addAttribute("projectVo" , projectVo);
        model.addAttribute("add" , vo);
        model.addAttribute("empVoList", empVoList);
        return "project/edit";
    }

    @PostMapping("edit")
    public String edit(ProjectVo vo, HttpSession session, ProjectMemberVo projectMemberVo){
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        int result = service.edit(vo, loginMemberVo);
        String prjKey = vo.getKey();
        int result2 = service.setAddMember(projectMemberVo, prjKey);

        return "redirect:/project/detail?projectNo=" + vo.getKey() ;
    }

    @GetMapping("delete")
    //프로젝트 삭제
    public String delete( @RequestParam("projectNo") String vo, HttpSession session){
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        int result = service.delete(vo, loginMemberVo);
        if(result != 1){
            return "redirect:/error";
        }
        return "redirect:/project/list";
    }

    @PostMapping("delMember")
    @ResponseBody
    public int deleteMember(String empNo, String key){
        int result = service.deleteMember(empNo, key);

        return result;
    }

    @PostMapping("addMember")
    @ResponseBody
    public ProjectMemberVo addMember(ProjectMemberVo empNo){
        ProjectMemberVo memberVo = service.addMemberVo(empNo);

        return memberVo;
    }


}


package com.semi.gam.schedule.controller;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.schedule.service.ScheduleService;
import com.semi.gam.schedule.vo.EventVo;
import com.semi.gam.schedule.vo.PriorityVo;
import com.semi.gam.schedule.vo.ScheduleVo;
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
@RequestMapping("schedule")
@Slf4j
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    // 일정 홈화면
    @GetMapping("home")
    public String home(HttpSession session){

        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");

        if(loginMemberVo == null){
            return "redirect:/member/login";
        }

        return "schedule/home";
    }

    // 일정 홈화면(이벤트 - ajax)
    @PostMapping("event")
    @ResponseBody
    public List<EventVo> event(HttpSession session, ScheduleVo vo){

        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());

        List<EventVo> evtVoList = service.getEventVoList(vo);

        return evtVoList;
    }

    // 새 일정 작성하기 화면
    @GetMapping("write")
    public String write(HttpSession session, Model model){

        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");

        if(loginMemberVo == null){
            return "redirect:/member/login";
        }

        List<PriorityVo> priorityVoList = service.getPriorityVo();
        model.addAttribute("priorityVoList", priorityVoList);

        return "schedule/write";
    }

    // 새 일정 작성하기 프로세스
    @PostMapping("write")
    public String write(ScheduleVo vo, HttpSession session) throws Exception {

        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());

        int result = service.write(vo);

        if(result != 1){
            throw new IllegalStateException("[ERROR-SCH-101]일정 작성하기 에러");
        }
        return "redirect:/schedule/home";
    }

    // 일정 수정하기 화면
    @GetMapping("edit")
    public String edit(Model model, String sno, HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        String writerNo = loginMemberVo.getId();

        List<PriorityVo> priorityVoList = service.getPriorityVo();

        ScheduleVo vo = service.getEventByNo(sno, writerNo);
        model.addAttribute("vo", vo);
        model.addAttribute("priorityVoList", priorityVoList);

        return "schedule/edit";
    }

    // 일정 수정하기 프로세스
    @PostMapping("edit")
    public String edit(ScheduleVo vo, HttpSession session, Model model){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());

        int result = service.edit(vo);


        if(result != 1){
            throw new IllegalStateException("[ERROR-SCH-201]일정 수정하기 에러");
        }

        return "redirect:/schedule/home";
    }

    // 일정 삭제하기 (ajax)
    @GetMapping("del")
    public String del(String sno, ScheduleVo vo, HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());

        int result = service.del(sno, vo);

        if(result != 1){
            throw new IllegalStateException("[ERROR-SCH-301]일정 삭제하기 에러");
        }
        return "redirect:/schedule/home";
    }

    // 일정 상세조회
    @GetMapping("detail")
    @ResponseBody
    public ScheduleVo detail(String sno, HttpSession session, Model model) {
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        String writerNo = loginMemberVo.getId();

        ScheduleVo vo = service.getEventByNo(sno, writerNo);
        model.addAttribute("vo", vo);

        return vo;
    }

    // 일정 중요도 ajax
    @GetMapping("priority")
    @ResponseBody
    public List<PriorityVo> priority(){
        List<PriorityVo> vo = service.getPriorityVo();

        return vo;
    }

}

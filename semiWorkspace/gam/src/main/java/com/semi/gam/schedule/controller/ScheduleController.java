package com.semi.gam.schedule.controller;

import com.semi.gam.schedule.service.ScheduleService;
import com.semi.gam.schedule.vo.ScheduleVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sch")
@Slf4j
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService ss;

    // 새 일정 작성하기 화면
    @GetMapping("write")
    public String write(){
        return "schedule/write";
    }

    // 새 일정 작성하기 프로세스
    @ResponseBody
    @PostMapping("write")
    public String write(ScheduleVo vo, HttpSession session) throws Exception {

//        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
//        vo.setWriterNo(loginMemberVo.getNo());

        int result = ss.write(vo);

//        if(result != 1){
//            throw new IllegalStateException("[ERROR-SCH-101]일정 작성하기 에러");
//        }
        return "result";
    }

    // 일정 수정하기 화면
    @GetMapping("edit")
    public String edit(){
        return "schedule/edit";
    }

    // 일정 수정하기 프로세스
    @PostMapping("edit")
    public void edit(ScheduleVo vo){

    }

    // 일정 목록조회 화면(월)
    @GetMapping("month")
    public String month(){
        return "schedule/month";
    }
    // 일정 목록조회 화면(주)
    @GetMapping("week")
    public String week(){
        return "schedule/week";
    }

    // 일정 목록조회 화면(일)
    @GetMapping("day")
    public void day(){

    }
}

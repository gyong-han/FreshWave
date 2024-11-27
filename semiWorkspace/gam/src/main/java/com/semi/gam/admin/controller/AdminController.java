package com.semi.gam.admin.controller;

import com.semi.gam.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("admin")
public class AdminController {

    //사원 추가(화면)
    @GetMapping("join")
    public String join(){
        return "member/join";
    }

    //사원 추가(데이터)
    @PostMapping("join")
    public void join(MemberVo vo){

    }

}

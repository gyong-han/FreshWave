package com.semi.gam.schedule.controller;

import com.semi.gam.schedule.service.ScheduleService;
import com.semi.gam.schedule.vo.ScheduleVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sch")
@Slf4j
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService ss;

    @GetMapping("write")
    public String write(){
        return "write";
    }

    @PostMapping("write")
    public void write(ScheduleVo vo){
        ss.write(vo);

        return "";
    }
}

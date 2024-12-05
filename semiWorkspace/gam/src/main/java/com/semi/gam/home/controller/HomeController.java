package com.semi.gam.home.controller;

import com.semi.gam.home.service.HomeService;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.project.vo.PriorityVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.xalan.xsltc.dom.NthIterator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService service;

    @GetMapping("home")
    public String home(Model model,HttpSession session){
        List<NoticeVo> redNoticeList = service.getRedNoticeList();
        List<NoticeVo> blueNoticeList = service.getBlueNoticeList();
        List<PriorityVo> chartList = service.getPriorityList();

        System.out.println("chartList = " + chartList);
        model.addAttribute("redNoticeList",redNoticeList);
        model.addAttribute("blueNoticeList",blueNoticeList);
        return "home";
    }

}

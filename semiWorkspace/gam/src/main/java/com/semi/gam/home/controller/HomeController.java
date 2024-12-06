package com.semi.gam.home.controller;

import com.semi.gam.home.service.HomeService;
import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.project.vo.PriorityVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService service;

    @GetMapping("home")
    public String home(Model model,HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
        List<NoticeVo> redNoticeList = service.getRedNoticeList();
        List<NoticeVo> blueNoticeList = service.getBlueNoticeList();

        model.addAttribute("redNoticeList",redNoticeList);
        model.addAttribute("blueNoticeList",blueNoticeList);
        return "home";
    }

    @GetMapping("home/data")
    @ResponseBody
    public List<PriorityVo> projectData(HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        String id = loginMemberVo.getId();
        List<PriorityVo> originChartList1 = service.getPriorityList1(id);
        List<PriorityVo> originChartList2 = service.getPriorityList2(id);
        List<PriorityVo> changeChartList1 = null;
        List<PriorityVo> changeChartList2 = null;

        if(originChartList1.size() > originChartList2.size()){
            changeChartList1 = originChartList1;
            changeChartList2 = originChartList2;
        }else{
            changeChartList1 = originChartList2;
            changeChartList2 = originChartList1;
        }

        for (PriorityVo vo1 : changeChartList1) {
            for (PriorityVo vo2 : changeChartList2) {
                if(vo1.getPriority().equals(vo2.getPriority())){
                    int combineCnt = Integer.parseInt(vo1.getCount()) + Integer.parseInt(vo2.getCount());
                    String cnt = String.valueOf(combineCnt);
                    vo1.setCount(cnt);
                    break;
                }
            }
        }
        return changeChartList1;
    }
}

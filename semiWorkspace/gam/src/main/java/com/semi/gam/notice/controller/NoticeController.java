package com.semi.gam.notice.controller;

import com.semi.gam.notice.service.NoticeService;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("notice")
public class NoticeController {

    private final NoticeService service;

    // 공지사항 작성 화면
    @GetMapping("write")
    public void write(){

    }

    // 공지사항 작성 처리
    @PostMapping("write")
    public String write(NoticeVo vo){
        int result = service.write(vo);

        if(result != 1) {
            new IllegalStateException("[ERROR] NOTICE > Controller > write");
        }
        return "redirect:/notice/list";
    }

    // 공지사항 목록
    @GetMapping("list")
    public String list(Model model , @RequestParam(name="pno" , defaultValue = "1" , required = false) int currentPage){
        int listCount = service.getNoticeCnt();
        int pageLimit = 5;
        int boardLimit = 8;

        PageVo pvo = new PageVo(listCount , currentPage, pageLimit, boardLimit);
        List<NoticeVo> voList = service.getNoticeList(pvo);

        for (NoticeVo notice : voList) {
            if ("Y".equals(notice.getUrgentYn())) {
                notice.setUrgentStatus("긴급"); // "긴급"으로 설정
            } else {
                notice.setUrgentStatus("일반"); // "일반"으로 설정
            }
        }

        model.addAttribute("voList" , voList);
        model.addAttribute("pvo" , pvo);

//        for(int i = 0; i < voList.size(); i++){
//            if(voList.get(i).getUrgentYn() == "Y"){
//        }

        return "notice/list";
    }

    // 공지사항 상세
    @GetMapping("detail")
    public String detail(String bno, Model model){
        NoticeVo vo = service.getNoticeDetail(bno);
        model.addAttribute("vo", vo);
        return "notice/detail";
    }
}
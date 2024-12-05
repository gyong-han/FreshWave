package com.semi.gam.notice.controller;

import com.semi.gam.board.vo.BoardVo;
import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.notice.service.NoticeService;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.FileUploader;
import com.semi.gam.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("notice")
public class NoticeController {

    private final NoticeService service;

    @Value(("#{pathInfo.getNoticeAttachmentPath()}"))
    private String path;

    // 공지사항 작성 화면
    @GetMapping("write")
    public String write(HttpSession session){
        if(session.getAttribute("loginMemberVo") == null){
            return "redirect:/member/login";
        }
        return "notice/write";
    }

    // 공지사항 작성 처리
    @PostMapping("write")
    public String write(NoticeVo vo , @RequestParam(name = "f") List<MultipartFile> fileList , @RequestParam(name = "urgentYn" , defaultValue = "N") String urgentYn, HttpSession session ) throws IOException {
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());

        List<String> changeNameList = new ArrayList<>();

        for(MultipartFile f : fileList){
            if(f.isEmpty()){break;}
            String changeName = FileUploader.save(f, path);
            changeNameList.add(changeName);
        }

        int result = service.write(vo , changeNameList);

        if(result != 1) {
            new IllegalStateException("[ERROR] NOTICE > Controller > write");
        }
        return "redirect:/notice/list";
    }

    // 공지사항 목록
    @GetMapping("list")
    public String list(Model model , @RequestParam(name="pno" , defaultValue = "1") int currentPage
                        ,String searchValue
                        ,String searchType){
        int listCount = service.getNoticeCnt(searchType , searchValue);
        int pageLimit = 5;
        int boardLimit = 8;

        PageVo pvo = new PageVo(listCount , currentPage, pageLimit, boardLimit);
        List<NoticeVo> voList = service.getNoticeList(pvo , searchType ,  searchValue);

        for (NoticeVo notice : voList) {
            if ("Y".equals(notice.getUrgentYn())) {
                notice.setUrgentStatus("긴급"); // "긴급"으로 설정
            } else {
                notice.setUrgentStatus("일반"); // "일반"으로 설정
            }
        }

        model.addAttribute("voList" , voList);
        model.addAttribute("pvo" , pvo);
        model.addAttribute("searchType" ,searchType);
        model.addAttribute("searchValue" , searchValue);
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

    // 공지사항 수정(화면)
    @GetMapping("edit")
    public void edit(Model model , String no){
        NoticeVo vo = service.getNoticeByNo(no);
        model.addAttribute("vo", vo);
    }

    // 공지사항 수정 처리
    @PostMapping("edit")
    public String edit(NoticeVo vo){
        int result =service.edit(vo);
        if(result != 1){
            throw new IllegalStateException("공지사항 수정 중 에러발생");
        }
        return "redirect:/notice/list";
    }

    // 공지사항 삭제
    @GetMapping("del")
    public String del(NoticeVo vo , HttpSession session){
        int result = service.del(vo);

        if(result != 1){
            throw new IllegalStateException("공지사항 삭제 실패");
        }
        session.setAttribute("alertMsg" , "공지사항 삭제");
        return "redirect:/notice/list";
    }

}
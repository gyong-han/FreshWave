package com.semi.gam.notice.controller;

import com.semi.gam.board.vo.AttachmentVo;
import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.notice.service.NoticeService;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.FileUploader;
import com.semi.gam.util.date.ChangeDate;
import com.semi.gam.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("notice")
public class NoticeController {

    private final NoticeService service;
    private final ChangeDate date;

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
    public String write(NoticeVo vo , @RequestParam(name = "f") MultipartFile f , @RequestParam(name = "urgentYn" , defaultValue = "N") String urgentYn, HttpSession session ) throws IOException {
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());
        vo.setUrgentYn(urgentYn);

        String changeName = "";
        String originName = f.getOriginalFilename();
        if(!f.isEmpty()){
            changeName = FileUploader.save(f,path);
        }

        int result = service.write(vo , changeName , originName);

        if(result != 1) {
            new IllegalStateException("[ERROR] NOTICE > Controller > write");
        }
        return "redirect:/notice/list";
    }

    // 공지사항 목록
    @GetMapping("list")
    public String list(Model model , HttpSession session,@RequestParam(name="pno" , defaultValue = "1" ) int currentPage
                        ,String searchValue
                        ,String searchType){

        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        model.addAttribute("jobCode" , loginMemberVo.getJobCode());
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

        for(NoticeVo vo : voList){
            if(vo.getEnrollDate() != null){
                vo.setEnrollDate(date.changeDate2(vo.getEnrollDate()));
            }
        }

        for(NoticeVo vo : voList){
            if(vo.getModifyDate() != null){
                vo.setModifyDate(date.changeDate2(vo.getModifyDate()));
            }
        }


        model.addAttribute("voList" , voList);
        model.addAttribute("pvo" , pvo);
        model.addAttribute("searchType" ,searchType);
        model.addAttribute("searchValue" , searchValue);
//

        return "notice/list";
    }

    // 공지사항 상세
    @GetMapping("detail")
    public String detail(String bno, Model model , HttpSession session){
        NoticeVo vo = service.getNoticeDetail(bno);
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        List<AttachmentVo> attachmentVoList = service.getAttachmentVoList(bno);
        model.addAttribute("vo", vo);
        model.addAttribute("attachmentVoList" , attachmentVoList);
        model.addAttribute("loginMemberVo" , loginMemberVo);
        return "notice/detail";
    }

    // 공지사항 수정(화면)
    @GetMapping("edit")
    public String edit(HttpSession session, Model model , String no){
        NoticeVo vo = service.getNoticeByNo(no);
        if(session.getAttribute("loginMemberVo") == null){
            return "redirect:/member/login";
        }
        model.addAttribute("vo", vo);
        return "notice/edit";
    }

    // 공지사항 수정 처리
    @PostMapping("edit")
    public String edit(NoticeVo vo ,  HttpSession session , @RequestParam(name = "urgentYn" , defaultValue = "N") String urgentYn, Model model, MultipartFile f) throws IOException {
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());
        vo.setUrgentYn(urgentYn);

        String changeName = "";
        String originName = "";
        if(f != null){
            originName = f.getOriginalFilename();
            changeName = FileUploader.save(f,path);
        }
        int result = service.edit(vo ,originName , changeName);
        session.setAttribute("alertNo",result);
        String bno = vo.getNo();
        if (bno == null || bno.isEmpty()) {
            throw new IllegalArgumentException("게시글 번호(bno)가 유효하지 않습니다."); // 예외 처리
        }

        session.setAttribute("changeName" , changeName);
        session.setAttribute("originName" , originName);

        return "redirect:/notice/detail?bno=" +bno;
    }

    // 공지사항 삭제
    @GetMapping("del")
    public String del(String bno, HttpSession session){
        int result = service.del(bno);

        if(result != 1){
            throw new IllegalStateException("공지사항 삭제 실패");
        }
        session.setAttribute("alertMsg" , "공지사항 삭제");
        return "redirect:/notice/list";
    }

}
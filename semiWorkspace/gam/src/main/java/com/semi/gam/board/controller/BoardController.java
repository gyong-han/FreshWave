package com.semi.gam.board.controller;

import com.semi.gam.board.service.BoardService;
import com.semi.gam.board.vo.AttachmentVo;
import com.semi.gam.board.vo.BoardVo;
import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.FileUploader;
import com.semi.gam.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    private final BoardService service;

    @Value("#{pathInfo.getBoardAttachmentPath()}")
    private String path;

    //홈 화면
    @GetMapping("home")
    public String home(Model model){
        List<BoardVo> boardVoList = service.getBoardHomeList();
        List<NoticeVo> noticeVoList = service.getNoticeHomeList();
        model.addAttribute("boardVoList", boardVoList);
        model.addAttribute("noticeVoList", noticeVoList);
        return "board/home";
    }

    //게시글 작성(화면)
    @GetMapping("write")
    public String insert(HttpSession session){
        if(session.getAttribute("loginMemberVo") == null){
            return "redirect:/member/login";
        }
        return "board/write";
    }

    //게시글 작성
    @PostMapping("write")
    public void insert(BoardVo vo , @RequestParam(name = "f") List<MultipartFile> fileList , HttpSession session) throws IOException {
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());
        System.out.println("loginMemberVo = " + loginMemberVo);

        List<String> changeNameList = new ArrayList<>();
        List<String> originNameList = new ArrayList<>();

        for (MultipartFile f : fileList){
            if(f.isEmpty()){break;}
            String originName = f.getOriginalFilename();
            vo.setOriginName(originName);
            String changeName = FileUploader.save(f, path);
            changeNameList.add(changeName);
        }
        //service
        int result = service.write(vo , changeNameList);
        System.out.println("vo = " + vo);
        //result
        if(result == 1){
            System.out.println("게시글 작성 성공 !");
        }else {
            System.out.println("게시글 작성 실패 ...");
        }
    }
    // 게시글 목록조회
    @GetMapping("list")
    public String list(){
        return "board/list";
    }

    // 게시글 목록조회(데이터)
    @GetMapping("list/data")
    @ResponseBody
    public HashMap boardVoList(@RequestParam(name = "pno" , defaultValue = "1" , required = false) int currentPage
                        ,String searchType
                        ,String searchValue){
        int listCount = service.getBoardCnt(searchValue ,searchValue);
        int pageLimit = 5;
        int boardLimit = 8;
        PageVo pvo = new PageVo(listCount , currentPage , pageLimit , boardLimit);

        List<BoardVo> boardVoList = service.getBoardList(pvo ,searchType ,searchValue);

        System.out.println("boardVoList = " + boardVoList);
        HashMap map = new HashMap<>();
        map.put("a" , boardVoList);
        map.put("b" , pvo);
        return map;
    }

    // 게시글 상세조회 (제목 , 닉네임, 등록일, 조회수 , 첨부파일 , 내용 , 댓글내용 , 댓글닉네임, 댓글등록일)
    @GetMapping("detail")
    public String detail(String bno, Model model){
        BoardVo vo = service.getBoardDetail(bno);
        List<AttachmentVo> attachmentVoList  = service.getAttachmentVoList(bno);
        model.addAttribute("vo" , vo);
        model.addAttribute("attachmentVoList" , attachmentVoList);
        return "board/detail";
    }

    // 게시글 수정(화면)
    @GetMapping("edit")
    public String edit(HttpSession session , Model model , @RequestParam String bno){
        BoardVo vo = service.getBoardDetail(bno);
        List<AttachmentVo> attachmentVoList = service.getAttachmentVoList(bno);
        System.out.println("bno = " + bno);
        System.out.println("vo1 = " + vo);
        if(session.getAttribute("loginMemberVo") == null){
            return "redirect:/member/login";
        }
        model.addAttribute("vo", vo);
        return "board/edit";
    }

    // 게시글 수정(처리)
    @PostMapping("edit")
    public String edit(BoardVo vo , @RequestParam(name = "f") List<MultipartFile> fileList , HttpSession session) throws IOException {
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());
        vo.setNo("126");
        List<String> changeNameList = new ArrayList<>();

        for(MultipartFile f :fileList){
            if(f.isEmpty()){break;}
            String changeName = FileUploader.save(f,path);
            changeNameList.add(changeName);
        }
        System.out.println("vo2 = " + vo);
        service.edit( vo ,changeNameList);
        session.setAttribute("alertMsg" , "게시글 수정 성공");
       return "redirect:/board/list";
    }

    // 게시글 삭제
    @GetMapping("del")
    public String del(String bno , HttpSession session){
        int result = service.del(bno);

        if(result != 1){
            throw new IllegalStateException("게시글 삭제 실패...");
        }
        session.setAttribute("alertMsg" , "삭제되었습니다.");
        return "redirect:/board/list";
    }

    // 게시글 댓글 작성

}


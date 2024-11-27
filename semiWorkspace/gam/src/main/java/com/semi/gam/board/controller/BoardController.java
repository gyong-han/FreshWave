package com.semi.gam.board.controller;

import com.semi.gam.board.service.BoardService;
import com.semi.gam.board.vo.BoardVo;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    private final BoardService service;

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
    public String insert(){
        return "board/write";
    }

    //게시글 작성
    @PostMapping("write")
    public void insert(BoardVo vo){
        //data -> obj
        System.out.println("title ::: " + vo.getTitle());
        //service
        int result = service.write(vo);

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
    public HashMap boardVoList(@RequestParam(name = "pno" , defaultValue = "1" , required = false) int currentPage){
        int listCount = service.getBoardCnt();
        int pageLimit = 5;
        int boardLimit = 8;
        PageVo pvo = new PageVo(listCount , currentPage , pageLimit , boardLimit);

        List<BoardVo> boardVoList = service.getBoardList(pvo);

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
        model.addAttribute("vo" , vo);
        return "board/detail";
    }
}

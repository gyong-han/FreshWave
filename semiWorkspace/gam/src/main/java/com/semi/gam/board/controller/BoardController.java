package com.semi.gam.board.controller;

import com.semi.gam.board.service.BoardService;
import com.semi.gam.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    private final BoardService service;
    
    //홈 화면
    @GetMapping("home")
    public String home(){
        return "board/home";
    }
    
    //게시글 작성(화면)
    @GetMapping("write")
    public String insert(){
        return "board/write";
    }

    //게시글 작성
    @PostMapping("insert")
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
//    // 게시글 목록조회
//    @GetMapping("list")
//    public String list(){
//        return "board/list";
//    }

    // 게시글 목록조회(데이터)
    @GetMapping("list")
    public List<BoardVo> boardVoList(){
        return service.getBoardList();
    }

    // 게시글 상세조회
    @GetMapping("detail")
    public String detail(String bno, Model model){
        BoardVo vo = service.getBoardDetail(bno);
        model.addAttribute("vo" , vo);
        return "board/detail";
    }
}

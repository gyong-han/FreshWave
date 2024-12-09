package com.semi.gam.board.controller;

import com.semi.gam.admin.vo.AdminVo;
import com.semi.gam.board.service.BoardService;
import com.semi.gam.board.vo.BoardVo;
import com.semi.gam.board.vo.CommentVo;
import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.FileUploader;
import com.semi.gam.util.date.ChangeDate;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    private final BoardService service;
    private final ChangeDate date;

    @Value("#{pathInfo.getBoardAttachmentPath()}")
    private String path;

    //홈 화면
    @GetMapping("home")
    public String home(Model model , HttpSession session){
        List<BoardVo> boardVoList = service.getBoardHomeList();
        List<NoticeVo> noticeVoList = service.getNoticeHomeList();
        model.addAttribute("boardVoList", boardVoList);
        model.addAttribute("noticeVoList", noticeVoList);

        MemberVo loginMemberVo  = (MemberVo) session.getAttribute("loginMemberVo");
        model.addAttribute("jobCode" , loginMemberVo.getJobCode());
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
    public String insert(BoardVo vo , @RequestParam(name = "f") MultipartFile f, HttpSession session) throws IOException {
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());

        String changeName = "";
        String originName = f.getOriginalFilename();
        if(!f.isEmpty()){
            changeName = FileUploader.save(f,path);
        }
        //service
        int result = service.write(vo , changeName, originName);

        //result
        if(result == 1){
            System.out.println("게시글 작성 성공 !");
        }else {
            System.out.println("게시글 작성 실패 ...");
        }
        return "redirect:/board/list";
    }
    // 게시글 목록조회
    @GetMapping("list")
    public String list(){
        return "board/list";
    }

    // 게시글 목록조회(데이터)
    @GetMapping("list/data")
    @ResponseBody
    public HashMap boardVoList(@RequestParam(name = "pno" , defaultValue = "1") int currentPage
                        ,String searchType
                        ,String searchValue
                        ,Model model){
        int listCount = service.getBoardCnt(searchType ,searchValue);
        int pageLimit = 5;
        int boardLimit = 8;
        PageVo pvo = new PageVo(listCount , currentPage , pageLimit , boardLimit);

        List<BoardVo> boardVoList = service.getBoardList(pvo ,searchType ,searchValue);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchValue", searchValue);

        // 날짜 형식 변경
        for(BoardVo vo : boardVoList){
            if(vo.getEnrollDate() != null){
                vo.setEnrollDate(date.changeDate2(vo.getEnrollDate()));
            }
        }

        System.out.println("boardVoList = " + boardVoList);
        System.out.println("listCount = " + listCount);

        HashMap map = new HashMap<>();
        map.put("a" , boardVoList);
        map.put("b" , pvo);
        return map;
    }

    // 게시글 상세조회 (제목 , 닉네임, 등록일, 조회수 , 첨부파일 , 내용 , 댓글내용 , 댓글닉네임, 댓글등록일)
    @GetMapping("detail")
    public String detail(String bno, Model model , HttpSession session ,String boardNo){

        BoardVo vo = service.getBoardDetail(bno);
//        List<AttachmentVo> attachmentVoList  = service.getAttachmentVoList(bno);
        model.addAttribute("vo" , vo);

        // 댓글 목록 조회 및 필터링
        List<CommentVo> allComments = service.getBoardCommentList(boardNo);
        List<CommentVo> filteredComments = new ArrayList<>();
        for (CommentVo comment : allComments) {
            if (comment.getBoardNo() != null && !comment.getBoardNo().isEmpty()) { // boardNo 유효성 체크
                comment.setBoardNo(boardNo); // boardNo 설정
                filteredComments.add(comment); // 유효한 댓글만 추가
            }
        }
        model.addAttribute("boardCommentVoList", filteredComments);
//        model.addAttribute("attachmentVoList" , attachmentVoList);

        return "board/detail";
    }

    // 게시글 수정(화면)
    @GetMapping("edit")
    public String edit(HttpSession session , Model model , @RequestParam String bno){
        BoardVo vo = service.getBoardDetail(bno);
//        List<AttachmentVo> attachmentVoList = service.getAttachmentVoList(bno);
        if(session.getAttribute("loginMemberVo") == null){
            return "redirect:/member/login";
        }
        model.addAttribute("vo", vo);
//        model.addAttribute("attachmentVoList" , attachmentVoList);
        return "board/edit";
    }

    // 게시글 수정(처리)
    @PostMapping("edit")
    public String edit(BoardVo vo , MultipartFile f, HttpSession session , Model model,String boardNo) throws IOException {
        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());

        String changeName = "";
        String originName = "";
        if (f != null){
            originName = f.getOriginalFilename();
            changeName = FileUploader.save(f, path);
        }
        int result = service.edit( vo , originName , changeName);
        session.setAttribute("alertNo" , result);
        detail(vo.getNo(),model,session,boardNo);

        String bno = vo.getNo();
        if (bno == null || bno.isEmpty()) {
            throw new IllegalArgumentException("게시글 번호(bno)가 유효하지 않습니다."); // 예외 처리
        }

        session.setAttribute("changeName" , changeName);
        session.setAttribute("originName" , originName);

        return "redirect:/board/detail?bno=" + bno;
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
    @PostMapping("comment/write")
    @ResponseBody
    public int commentWrite(CommentVo vo , HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        vo.setComWriNo(loginMemberVo.getId());
        int result = service.commentWrite(vo);
        return result;
    }

    // 게시글 댓글 목록 조회
    @GetMapping("comment/list")
    @ResponseBody
    public List<CommentVo> getBoardCommentList(String boardNo){
        List<CommentVo> BoardCommentVoList = service.getBoardCommentList(boardNo);
        return BoardCommentVoList;
    }

    // 게시글 댓글 삭제
    @PostMapping("comment/del")
    @ResponseBody
    public String commentDel(CommentVo vo , HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        vo.setComWriNo(loginMemberVo.getId());
        int result  = service.commentDel(vo);
        if(result == 0){
            return "bad";
        }
        return "good";
    }

}


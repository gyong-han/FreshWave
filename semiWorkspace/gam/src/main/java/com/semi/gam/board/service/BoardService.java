package com.semi.gam.board.service;

import com.semi.gam.board.mapper.BoardMapper;
import com.semi.gam.board.vo.AttachmentVo;
import com.semi.gam.board.vo.BoardVo;
import com.semi.gam.board.vo.CommentVo;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardService {

    private final BoardMapper mapper;

    public List<BoardVo> getBoardList(PageVo pvo , String searchType , String searchValue) {
        return mapper.getBoardList(pvo , searchType, searchValue);
    }

    public BoardVo getBoardDetail(String bno) {
        int result = mapper.increseHit(bno);
//        if(result != 1){
//            String errMsg = "BOARD > SERVICE > 상세조회 > 조회수 에러";
//            log.error(errMsg);
//            throw new IllegalStateException(errMsg);
//        }
        return mapper.getBoardDetail(bno);
    }

    public int getBoardCnt(String searchType, String searchValue) {
        return mapper.getBoardCnt(searchType , searchValue);
    }

    public List<BoardVo> getBoardHomeList() {
        return mapper.getBoardHomeList();
    }

    public List<NoticeVo> getNoticeHomeList() {
        return mapper.getNoticeHomeList();
    }

    public int edit(BoardVo vo, String originName , String changeName) {
        int result1 =  mapper.getBoardEdit(vo);
        if(result1 != 1){
            throw new IllegalStateException("ERROR > BOARD > UPDATE > result1");
        }
        int result2 = 1;
        if(!originName.isEmpty() && !changeName.isEmpty()){
            result2 = mapper.editAttachment(vo, originName , changeName);
        }
        return result1 * result2;
    }

    public int del(String bno) {
        int result = mapper.increseHit(bno);
        return mapper.del(bno);
    }

    public List<AttachmentVo> getAttachmentVoList(String bno) {
        return mapper.getAttachmentVoList(bno);
    }

    public int commentWrite(CommentVo vo) {
        return mapper.commentWrite(vo);
    }

    public List<CommentVo> getBoardCommentList(String boardNo) {
        return mapper.getBoardCommentList(boardNo);
    }

    public int commentDel(CommentVo vo) {
        return mapper.commentDel(vo);
    }


    public int write(BoardVo vo, String changeName, String originName) {

        int result1 = mapper.write(vo);
        int result2 = mapper.insertBoardAttachment(changeName , originName);
        return result1 * result2;
    }
}
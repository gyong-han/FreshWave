package com.semi.gam.board.service;

import com.semi.gam.board.mapper.BoardMapper;
import com.semi.gam.board.vo.BoardVo;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardService {

    private final BoardMapper mapper;

    public List<BoardVo> getBoardList(PageVo pvo) {
        return mapper.getBoardList(pvo);
    }

    public int write(BoardVo vo , List<String> changeNameList) {

        int result1 =  mapper.write(vo);
        int result2 = 1;
        if(changeNameList.size() > 0 ){
            result2 = mapper.insertBoardAttachment(changeNameList);
        }
        return result1 * result2;
    }

    public BoardVo getBoardDetail(String bno) {
        int result = mapper.increseHit(bno);
        if(result != 1){
            String errMsg = "BOARD > SERVICE > 상세조회 > 조회수 에러";
            log.error(errMsg);
            throw new IllegalStateException(errMsg);
        }
        return mapper.getBoardDetail(bno);
    }

    public int getBoardCnt() {
        return mapper.getBoardCnt();
    }

    public List<BoardVo> getBoardHomeList() {
        return mapper.getBoardHomeList();
    }

    public List<NoticeVo> getNoticeHomeList() {
        return mapper.getNoticeHomeList();
    }
}
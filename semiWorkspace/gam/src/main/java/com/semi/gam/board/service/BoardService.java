package com.semi.gam.board.service;

import com.semi.gam.board.mapper.BoardMapper;
import com.semi.gam.board.vo.BoardVo;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardMapper mapper;

    public List<BoardVo> getBoardList(PageVo pvo) {
        return mapper.getBoardList(pvo);
    }

    public int write(BoardVo vo) {
        return mapper.write(vo);
    }

    public BoardVo getBoardDetail(String bno) {
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
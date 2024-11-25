package com.semi.gam.board.service;

import com.semi.gam.board.mapper.BoardMapper;
import com.semi.gam.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;

    public List<BoardVo> getBoardList() {
        return mapper.getBoardList();
    }

    public int write(BoardVo vo) {
        return mapper.write(vo);
    }

    public BoardVo getBoardDetail(String bno) {
        return mapper.getBoard(bno);
    }
}

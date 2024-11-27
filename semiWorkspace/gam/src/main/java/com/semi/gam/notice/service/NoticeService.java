package com.semi.gam.notice.service;

import com.semi.gam.notice.mapper.NoticeMapper;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {

    private final NoticeMapper mapper;

    public int write(NoticeVo vo) {
        return mapper.write(vo);
    }

    public int getNoticeCnt() {
        return mapper.getNoticeCnt();
    }

    public List<NoticeVo> getNoticeList(PageVo pvo) {
        return mapper.getNoticeList(pvo);
    }

    public NoticeVo getNoticeDetail(String bno) {
        return mapper.getNoticeDetail(bno);
    }
}
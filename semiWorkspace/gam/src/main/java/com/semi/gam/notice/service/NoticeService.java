package com.semi.gam.notice.service;

import com.semi.gam.notice.mapper.NoticeMapper;
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
public class NoticeService {

    private final NoticeMapper mapper;

    public int write(NoticeVo vo , List<String> changeNameList) {

        int result1 = mapper.write(vo);
        int result2 = 1;
        if(changeNameList.size() > 0){
            result2 = mapper.insertNoticeAttachment(changeNameList);
        }
        return result1 * result2;
    }

    public int getNoticeCnt() {
        return mapper.getNoticeCnt();
    }

    public List<NoticeVo> getNoticeList(PageVo pvo) {
        return mapper.getNoticeList(pvo);
    }

    public NoticeVo getNoticeDetail(String bno) {
        int result = mapper.increseHit(bno);
        if(result != 1){
            String errMsg = "NOTICE > SERVICE > 상세조회 > 조회수 에러";
            log.error(errMsg);
            throw new IllegalStateException(errMsg);
        }
        return mapper.getNoticeDetail(bno);
    }
}
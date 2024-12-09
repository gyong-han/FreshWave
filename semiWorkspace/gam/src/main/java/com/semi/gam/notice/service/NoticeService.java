package com.semi.gam.notice.service;

import com.semi.gam.board.vo.AttachmentVo;
import com.semi.gam.board.vo.BoardVo;
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

    public int write(NoticeVo vo , String changeName, String originName) {

        int result1 = mapper.write(vo);
        int result2 = mapper.insertNoticeAttachment(changeName , originName);
        return result1 * result2;
    }

    public int getNoticeCnt(String searchType , String searchValue) {
        return mapper.getNoticeCnt(searchType , searchValue);
    }

    public List<NoticeVo> getNoticeList(PageVo pvo , String searchType, String searchValue) {
        return mapper.getNoticeList(pvo , searchType , searchValue);
    }

    public NoticeVo getNoticeDetail(String bno) {
        int result = mapper.increseHit(bno);
//        if(result != 1){
//            String errMsg = "NOTICE > SERVICE > 상세조회 > 조회수 에러";
//            log.error(errMsg);
//            throw new IllegalStateException(errMsg);
//        }
        return mapper.getNoticeDetail(bno);
    }

    public int edit(NoticeVo vo ,String originName, String changeName) {
        int result1 = mapper.edit(vo);
        int result2 = 1;
        if(!originName.isEmpty() && !changeName.isEmpty()){
            result2 = mapper.editAttachment(vo,originName,changeName);
        }
        return result1 * result2;
    }

    public NoticeVo getNoticeByNo(String no) {
        return mapper.getNoticeByNo(no);
    }

    public int del(String bno) {
        return mapper.del(bno);
    }

    public List<AttachmentVo> getAttachmentVoList(String bno) {
        return mapper.getAttachmentVoList(bno);
    }
}
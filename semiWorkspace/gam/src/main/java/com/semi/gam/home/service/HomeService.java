package com.semi.gam.home.service;

import com.semi.gam.home.mapper.HomeMapper;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.project.vo.PriorityVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeService {
    private final HomeMapper mapper;

    public List<NoticeVo> getRedNoticeList() {
        return mapper.getRedNoticeList();
    }

    public List<NoticeVo> getBlueNoticeList() {
        return mapper.getBlueNoticeList();
    }

    public List<PriorityVo> getPriorityList1(String id) {
        return mapper.getPriorityList1(id);
    }

    public List<PriorityVo> getPriorityList2(String id) {
        return mapper.getPriorityList2(id);
    }
}

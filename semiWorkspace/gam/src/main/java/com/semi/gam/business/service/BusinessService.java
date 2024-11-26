package com.semi.gam.business.service;

import com.semi.gam.business.mapper.BusinessMapper;
import com.semi.gam.business.vo.BusinessVo;
import com.semi.gam.business.vo.RankVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessMapper mapper;

    public int insert(BusinessVo vo, String changeName, String originName) {
        int result1 = mapper.insert(vo);
        int result2 = mapper.insertHistory(vo);
        int result3 = mapper.insertAttachment(changeName,originName);
        return result1 * result2;
    }

    public List<BusinessVo> getBusinessVoList() {
        return mapper.getBusinessVoList();
    }

    public BusinessVo detail(String bno) {
        return mapper.detail(bno);
    }

    public List<RankVo> getDataList() {
        return mapper.getDataList();
    }
}

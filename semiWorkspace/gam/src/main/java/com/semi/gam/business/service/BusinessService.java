package com.semi.gam.business.service;

import com.semi.gam.business.mapper.BusinessMapper;
import com.semi.gam.business.vo.BtCodeVo;
import com.semi.gam.business.vo.BusinessVo;
import com.semi.gam.business.vo.RankVo;
import com.semi.gam.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BusinessService {
    private final BusinessMapper mapper;

    public int insert(BusinessVo vo, String changeName, String originName) {
        int result1 = mapper.insert(vo);
        int result2 = mapper.insertHistory(vo);
        int result3 = mapper.insertAttachment(changeName,originName);
        return result1 * result2;
    }

    public List<BusinessVo> getBusinessVoList(PageVo pvo, String searchType, String searchValue) {
        return mapper.getBusinessVoList(pvo,searchType,searchValue);
    }

    public BusinessVo detail(String no) {
        return mapper.detail(no);
    }

    public List<RankVo> getDataList() {
        return mapper.getDataList();
    }

    public int getBusinessCnt(String searchValue, String searchType) {
        return mapper.getBusinessCnt(searchValue,searchType);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

    public int edit(BusinessVo vo, String originName, String changeName) {
        int result1 = mapper.edit(vo);
        int result2 = mapper.editHistory(vo);
        int result3 = 1;
        if(!originName.isEmpty() && !changeName.isEmpty()){
            result3 = mapper.editAttachment(vo,originName,changeName);
        }
        return result1 * result2 * result3;
    }

    public List<BtCodeVo> getBtCodeList() {
        return mapper.getBtCodeList();
    }
}

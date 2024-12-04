package com.semi.gam.store.service;

import com.semi.gam.store.mapper.StoreMapper;
import com.semi.gam.store.vo.StatusVo;
import com.semi.gam.store.vo.StoreVo;
import com.semi.gam.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {
    private final StoreMapper mapper;


    public int insert(StoreVo vo, String changeName, String originName) {

        int result1 = mapper.insert(vo);
        int result2 = mapper.insertAttachment(changeName,originName);
        return result1 * result2;
    }

    public List<StoreVo> getStoreVoList(PageVo pvo, String searchType, String searchValue) {
        return mapper.getStoreVoList(pvo,searchType,searchValue);
    }

    public StoreVo detail(String no) {
        return mapper.detail(no);
    }

    public int getStoreCnt(String searchType, String searchValue) {
        return mapper.getStoreCnt(searchValue,searchType);
    }

    public int delete(String bno) {
        return mapper.delete(bno);
    }

    public int edit(StoreVo svo, String changeName, String originName) {
        int result1 =  mapper.edit(svo);
        int result2 = 1;
        if(!originName.isEmpty() && !changeName.isEmpty()){
            result2 = mapper.editAttachment(svo,originName,changeName);
        }
        return result1 * result2;
    }

    public List<StatusVo> storeData() {
        return mapper.storeData();
    }
}
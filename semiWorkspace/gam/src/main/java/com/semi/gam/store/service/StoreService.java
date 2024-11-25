package com.semi.gam.store.service;

import com.semi.gam.store.mapper.StoreMapper;
import com.semi.gam.store.vo.StoreVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {
    private final StoreMapper mapper;


    public int insert(StoreVo vo, String changeName) {
        int result1 = mapper.insert(vo);
        int result2 = mapper.insertAttachment(changeName);
        return result1 * result2;
    }
}
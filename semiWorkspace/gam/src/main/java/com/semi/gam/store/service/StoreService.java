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


    public int insert(StoreVo vo, List<String> changeNameList) {
        int result1 = mapper.insert(vo);
        int result2 = 1;
        if (changeNameList.size() > 0) {
            System.out.println("111");
            result2 = mapper.insertAttachment(changeNameList);
            System.out.println("2222");
        }
        return result1 * result2;
    }
}
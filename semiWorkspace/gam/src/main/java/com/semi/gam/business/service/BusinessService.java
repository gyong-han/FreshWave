package com.semi.gam.business.service;

import com.semi.gam.business.mapper.BusinessMapper;
import com.semi.gam.business.vo.BusinessVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessMapper mapper;

    public int insert(BusinessVo vo, String changeName) {
        int result1 = mapper.insert(vo);
        int result2 = mapper.insertHistory(vo);
        int result3 = mapper.insertAttachment(changeName);
        return result1 * result2;
    }
}

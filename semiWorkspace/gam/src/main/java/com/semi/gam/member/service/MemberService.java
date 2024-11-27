package com.semi.gam.member.service;

import com.semi.gam.member.mapper.MemberMapper;
import com.semi.gam.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberMapper mapper;

    public List<MemberVo> getEmpVo() {
        return mapper.getEmpVo();
    }
}

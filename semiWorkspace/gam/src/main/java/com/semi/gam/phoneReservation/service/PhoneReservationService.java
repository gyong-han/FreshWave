package com.semi.gam.phoneReservation.service;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.phoneReservation.mapper.PhoneReservationMapper;
import com.semi.gam.phoneReservation.vo.PhoneReservationVo;
import com.semi.gam.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PhoneReservationService {

    private final PhoneReservationMapper mapper;

    public int write(PhoneReservationVo vo, MemberVo loginMemberVo) {
        // biz
        String errCode = null;

        if(vo.getContent() == null){
            errCode = "[ERR-PRV-001] 내용은 필수로 입력해주세요";
        }
        if(vo.getCpCode() == null && vo.getCpCode() != loginMemberVo.getCpCode()){
            errCode = "[ERR-PRV-002] 회사코드가 로그인 정보와 일치하지 않습니다. 다시 입력해주세요.";
        }
        if(vo.getPhone() == null && vo.getPhone() != loginMemberVo.getPhone()){
            errCode = "[ERR-PRV-003] 전화번호가 로그인 정보와 일치하지 않습니다. 다시 입력해주세요.";
        }

        if(errCode != null){
            log.warn(errCode);
            throw new IllegalStateException(errCode);
        }

        int result = mapper.write(vo);

        return result;
    }

    public int edit(PhoneReservationVo vo, String rno) {

        return mapper.edit(vo, rno);
    }

    public void verification(PhoneReservationVo vo){
        // biz
        String errCode = null;

        if(vo.getContent() == null){
            errCode = "[ERR-PRV-001] 내용은 필수로 입력해주세요";
        }
//        if(vo.getCpCode() != null && vo.getCpCode() != loginMemberVo.getCpCode()){
//            errCode = "[ERR-PRV-002] 회사코드가 로그인 정보와 일치하지 않습니다. 다시 입력해주세요.";
//        }
//        if(vo.getPhone() != null && vo.getPhone() != loginMemberVo.getPhone()){
//            errCode = "[ERR-PRV-003] 전화번호가 로그인 정보와 일치하지 않습니다. 다시 입력해주세요.";
//        }

        if(errCode != null){
            log.warn(errCode);
            throw new IllegalStateException(errCode);
        }
    }

    public int getReservationCnt(String searchType, String searchValue) {
        return mapper.getReservationCnt(searchType, searchValue);
    }

    public PhoneReservationVo getReservation() {
        return mapper.getReservation();
    }

    public List<PhoneReservationVo> getReservationVoList(PageVo pvo, String searchType, String searchValue) {
        return mapper.getReservationVoList(pvo,searchType,searchValue);
    }

    public PhoneReservationVo getReservationByNo(String rno, String id) {
        return mapper.getReservationByNo(rno, id);
    }

    public int del(String rno, PhoneReservationVo vo) {
        return mapper.del(rno, vo);
    }
}

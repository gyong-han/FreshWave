package com.semi.gam.member.service;

import com.semi.gam.member.mapper.MemberMapper;
import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import com.semi.gam.admin.vo.AdminVo;
import com.semi.gam.dept.vo.DeptVo;
import com.semi.gam.employee.vo.EmployeeVo;
import com.semi.gam.job.vo.JobVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberMapper mapper;

    public List<MemberVo> getEmpVo() {
        return mapper.getEmpVo();
    }

    public MemberVo login (MemberVo mvo){
        return mapper.loginMember(mvo);
    }

    public AdminVo loginAdmin (AdminVo avo){
        return mapper.loginAdmin(avo);
    }

    public MemberVo loginMember (MemberVo mvo){
        return mapper.loginMember(mvo);
    }

    public List<JobVo> getJobVoList() {
        return mapper.getJobVoList();
    }

    public List<DeptVo> getDeptVoList() {
        return mapper.getDeptVoList();
    }

    public int join (MemberVo mvo, EmployeeVo evo){
        int result1 = mapper.Company(evo);
        int result2 = mapper.join(mvo);
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
        return result2 * result1;
    }


    public int stratlogin(EmployeeVo evo) {
        return mapper.stratlogin(evo);
    }

    public MemberVo edit(MemberVo vo) {
        int result = mapper.edit(vo);
//        if(result != 1){
////            throw new IllegalStateException("ERROR-MYPAGE-EDIT-SERVICE");
//        }
        MemberVo updateMemberVo = mapper.getMember(vo);
        return updateMemberVo;
    }


    public boolean checkDupNick(String nick) {
        MemberVo vo = mapper.checkDupNick(nick);
        return vo != null;
    }

    public int logOutFinish(MemberVo loginMemberVo) {
        return mapper.logOutFinish(loginMemberVo);
    }

    public int getMemberCnt(String searchType, String searchValue) {
        return mapper.getMemberCnt(searchType,searchValue);
    }

    public List<EmployeeVo> getMemberList(PageVo pvo, String searchType, String searchValue) {
        return mapper.getMemberList(pvo,searchType,searchValue);
    }
}
package com.semi.gam.member.service;

import com.semi.gam.admin.vo.AdminVo;
import com.semi.gam.dept.vo.DeptVo;
import com.semi.gam.employee.vo.EmployeeVo;
import com.semi.gam.job.vo.JobVo;
import com.semi.gam.member.mapper.MemberMapper;
import com.semi.gam.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
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

    public MemberVo login(MemberVo mvo) {
        return mapper.loginMember(mvo);

    }

    public AdminVo loginAdmin(AdminVo avo) {
        return mapper.loginAdmin(avo);
    }

    public MemberVo loginMember(MemberVo mvo) {
        return mapper.loginMember(mvo);
    }

    public int join(MemberVo mvo, EmployeeVo evo) {
        int result1 = mapper.Company(evo);
        int result2 = mapper.join(mvo);
        return result2*result1;
    }


    public List<JobVo> getJobVoList() {
        return mapper.getJobVoList();
    }

    public List<DeptVo> getDeptVoList() {
        return mapper.getDeptVoList();
    }
}

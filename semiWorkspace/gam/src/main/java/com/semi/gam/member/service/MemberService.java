package com.semi.gam.member.service;

<<<<<<< HEAD
import com.semi.gam.member.mapper.MemberMapper;
import com.semi.gam.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
=======
import com.semi.gam.admin.vo.AdminVo;
import com.semi.gam.dept.vo.DeptVo;
import com.semi.gam.employee.vo.EmployeeVo;
import com.semi.gam.job.vo.JobVo;
import com.semi.gam.member.mapper.MemberMapper;
import com.semi.gam.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
>>>>>>> 5e412a01822d795dc01dfb9bae757f28dc21d6b5
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
<<<<<<< HEAD
=======
@Slf4j
>>>>>>> 5e412a01822d795dc01dfb9bae757f28dc21d6b5
public class MemberService {

    private final MemberMapper mapper;

<<<<<<< HEAD
    public List<MemberVo> getEmpVo() {
        return mapper.getEmpVo();
=======
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
>>>>>>> 5e412a01822d795dc01dfb9bae757f28dc21d6b5
    }
}

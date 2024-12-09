package com.semi.gam.admin.service;

import com.semi.gam.admin.mapper.AdminMapper;
import com.semi.gam.dept.vo.DeptVo;
import com.semi.gam.employee.vo.EmployeeVo;
import com.semi.gam.job.vo.JobVo;
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
public class AdminService {

    private final AdminMapper mapper;

    public int getMemberCnt(String searchType, String searchValue) {
        return mapper.getMemberCnt(searchType,searchValue);
    }

    public List<EmployeeVo> getMemberList(PageVo pvo, String searchType, String searchValue) {
        return mapper.getMemberList(pvo,searchType,searchValue);
    }

    public EmployeeVo getMember(String no) {
        return mapper.getMember(no);
    }

    public int edit(EmployeeVo vo) {
        return mapper.edit(vo);
    }


    public int del(String no) {
        return mapper.del(no);
    }

    public List<EmployeeVo> getMemberListDel(PageVo pvo, String searchType, String searchValue) {
        return mapper.getMemberListDel(pvo,searchType,searchValue);
    }

    public int getMemberCntDel(String searchType, String searchValue) {
        return mapper.getMemberCntDel(searchType,searchValue);
    }

    public EmployeeVo getMemberDel(String no) {
        return mapper.getMemberDel(no);
    }

    public int out(String no) {
        return mapper.out(no);
    }

    public List<EmployeeVo> getMemberHomeList() {
        return mapper.getMemberHomeList();
    }
}

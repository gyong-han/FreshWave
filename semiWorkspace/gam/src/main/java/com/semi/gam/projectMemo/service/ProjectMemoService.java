package com.semi.gam.projectMemo.service;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.projectMemo.mapper.ProjectMemoMapper;
import com.semi.gam.projectMemo.vo.ProjectMemoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectMemoService {

    private final ProjectMemoMapper mapper;

    public int write(ProjectMemoVo vo, MemberVo loginMemberVo) {

        //vo에 들어있는 store 문자열

        return mapper.write(vo, loginMemberVo);
    }

    public List<ProjectMemoVo> list(String key) {
        return mapper.list(key);
    }

    public ProjectMemoVo getMemoVo(String no) {
        return mapper.getMemoVo(no);
    }

    public int edit(ProjectMemoVo vo) {
        return mapper.edit(vo);
    }
}

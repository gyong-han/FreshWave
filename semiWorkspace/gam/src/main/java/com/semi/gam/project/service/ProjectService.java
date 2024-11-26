package com.semi.gam.project.service;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.mapper.ProjectMapper;
import com.semi.gam.project.vo.ProjectVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectMapper mapper;

    public int write(ProjectVo vo) {
        return mapper.write(vo);
    }

    public int edit(ProjectVo vo, MemberVo memberVo) {
        return mapper.edit(vo, memberVo);
    }

    public ProjectVo delete(ProjectVo vo) {
        return mapper.delete(vo);

    }
}

package com.semi.gam.project.service;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.mapper.ProjectMapper;
import com.semi.gam.project.vo.ProjectMemberVo;
import com.semi.gam.project.vo.ProjectVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectMapper mapper;

    public int write(ProjectVo vo, MemberVo memberVo) {
        return mapper.write(vo, memberVo);
    }

    public int edit(ProjectVo vo, MemberVo memberVo) {
        return mapper.edit(vo, memberVo);
    }

    public ProjectVo delete(ProjectVo vo) {
        return mapper.delete(vo);

    }

    public List<ProjectVo> getProjectList(MemberVo loginMemberVo) {
        return mapper.getProjectList(loginMemberVo);
    }

    public ProjectVo getProject(String key, MemberVo loginMemberVo) {
        return mapper.getProject(key,loginMemberVo);
    }

//    public List<ProjectVo> getProjectAddMemberVo(MemberVo loginMemberVo) {
//        return mapper.getProjectAddMemberVo(loginMemberVo);
//    }

    public ProjectVo getProject1(String key, MemberVo loginMemberVo) {
        return mapper.getProject1(key,loginMemberVo);
    }

    public List<ProjectVo> getProjectAddMemberVo(MemberVo loginMemberVo) {
        return mapper.getProjectAddMemberVo(loginMemberVo);
    }

    public ProjectMemberVo getAddMember(String key, MemberVo loginMemberVo) {
        return mapper.getAddMember(key, loginMemberVo);
    }
}

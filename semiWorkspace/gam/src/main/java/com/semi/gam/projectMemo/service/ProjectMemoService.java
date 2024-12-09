package com.semi.gam.projectMemo.service;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.vo.PageVo;
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

    public List<ProjectMemoVo> list(String key, PageVo pvo) {
        return mapper.list(key, pvo);
    }

    public ProjectMemoVo getMemoVo(String no) {
        return mapper.getMemoVo(no);
    }

    public int edit(ProjectMemoVo vo) {
        return mapper.edit(vo);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

    public List<ProjectMemoVo> getProjectMemoWaitList(MemberVo loginMemberVo, String key) {
        return mapper.getProjectMemoWaitList(loginMemberVo, key);
    }

    public List<ProjectMemoVo> getProjectMemoIngList(MemberVo loginMemberVo, String key) {
        return mapper.getProjectMemoIngList(loginMemberVo , key);
    }

    public List<ProjectMemoVo> getProjectMemoComplateList(MemberVo loginMemberVo, String key) {
        return mapper.getProjectMemoComplateList(loginMemberVo, key);
    }

    public int getProjectMemoListCnt(MemberVo loginMemberVo, String key) {
        return mapper.getProjectMemoListCnt(loginMemberVo, key);
    }

    public ProjectMemoVo getProjectName(String key) {
        return mapper.getProjectName(key);
    }
}

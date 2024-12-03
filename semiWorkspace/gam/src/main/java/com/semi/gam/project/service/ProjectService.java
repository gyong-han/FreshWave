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

    public int edit(ProjectVo vo, MemberVo loginMemberVo) {
        String[] access = vo.getAccess();
        String[] empNo = vo.getEmpNo();

        for(int i = 0; i < access.length; i++){
            if(access[i].equals("수정")){
                mapper.addMemberEdit(empNo[i], access[i], vo);
            }
            if(access[i].equals("읽기")){
                mapper.addMemberRead(empNo[i],access[i], vo);
            }
        }
        return mapper.edit(vo, loginMemberVo);
    }

    public int delete(String vo, MemberVo loginMemberVo) {
        return mapper.delete(vo , loginMemberVo);

    }

    public List<ProjectVo> getProjectList(MemberVo loginMemberVo) {
        return mapper.getProjectList(loginMemberVo);
    }

    public ProjectVo getProject(String key, MemberVo loginMemberVo) {
        return mapper.getProject(key,loginMemberVo);
    }


    public List<ProjectVo> getProjectAddMemberVo(MemberVo loginMemberVo) {
        return mapper.getProjectAddMemberVo(loginMemberVo);
    }

    public List<ProjectMemberVo> getAddMember(String key) {
        return mapper.getAddMember(key);
    }

    public int deleteMember(String empNo, String key) {
        return mapper.deleteMember(empNo, key);
    }
}

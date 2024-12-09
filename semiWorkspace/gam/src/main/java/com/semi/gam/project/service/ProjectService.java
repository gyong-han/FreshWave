package com.semi.gam.project.service;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.mapper.ProjectMapper;
import com.semi.gam.project.vo.PageVo;
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

       if(vo.getAccess() != null){
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
        }
        return mapper.edit(vo, loginMemberVo);
    }

    public int delete(String vo, MemberVo loginMemberVo) {
        return mapper.delete(vo , loginMemberVo);

    }

    public List<ProjectVo> getProjectList(MemberVo loginMemberVo, PageVo pvo) {

        List<ProjectVo> projectVoList1 = mapper.getProjectAddMemberVo(loginMemberVo, pvo);

        List<ProjectVo> projectVoList2 = mapper.getProjectList(loginMemberVo, pvo);
        projectVoList1.addAll(projectVoList2);
        return projectVoList1;
    }

    public List<ProjectVo> getProjectCardList(MemberVo loginMemberVo) {

        List<ProjectVo> projectVoList1 = mapper.getProjectCardAddMemberVo(loginMemberVo);
        List<ProjectVo> projectVoList2 = mapper.getProjectCardList(loginMemberVo);

        projectVoList1.addAll(projectVoList2);

        return projectVoList1;
    }

    public ProjectVo getProject(String key, MemberVo loginMemberVo) {
        return mapper.getProject(key,loginMemberVo);
    }


    public List<ProjectMemberVo> getAddMember(String key) {
        return mapper.getAddMember(key);
    }

    public int deleteMember(String empNo, String key) {
        return mapper.deleteMember(empNo, key);
    }

    public List<ProjectMemberVo> getAddMemberVo() {
        return mapper.getAddMemberVo();
    }


    public ProjectMemberVo addMemberVo(ProjectMemberVo empNo) {
        return mapper.addMemberVp(empNo);
    }

    public String getProjectKey(ProjectVo vo, MemberVo loginMemberVo) {

        return mapper.getProjectKey(vo, loginMemberVo);
    }

    public int setAddMember(ProjectMemberVo projectMemberVo, String prjKey) {

        int result = 0;
        String[] empNo = projectMemberVo.getEmpNoArr();
        String[] modiAuth = projectMemberVo.getModiAuthArr();

        if(empNo != null){
            for(int i = 0; i < empNo.length; i++){
                String empNoArr = empNo[i];
                String modiAuthArr = modiAuth[i];
                result = mapper.setAddMember(empNoArr, modiAuthArr, prjKey);
            }
        }

        return result;
    }

    public int getProjectListCnt(MemberVo loginMemberVo) {
        int result1 = mapper.getProjectListCnt(loginMemberVo);
        int result2 = mapper.getProjectAddListCnt(loginMemberVo);
        int result = result1 + result2;
        return result;
    }
}

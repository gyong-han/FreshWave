package com.semi.gam.project.mapper;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.vo.ProjectVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface ProjectMapper {

    @Insert("""
            INSERT INTO PROJECT
            (
                 WRITER_NO
                , PRIORITY
                , ACCESS_LEVEL
                , NAME
                , START_DATE
                , END_DATE
            )
            VALUES
            (
                 #{memberVo.empNo}
                , #{vo.priority}
                , #{vo.accessLevel}
                , #{vo.name}
                , #{vo.startDate}
                , #{vo.endDate}
            )
            
            """)
    int write(ProjectVo vo, MemberVo memberVo);


    @Update("""
            UPDATE PROJECT
                SET
                    NAME = #{name}
                    , PRIORITY = #{priority}
                    , ACCESS_LEVEL = #{accessLevel}
                    , START_DATE = {startDate}
                    , END_DATE = {endDate}
                    , MODIFY_DATE = SYSDATE
                WHERE DEL_YN = 'N'
                AND WRITER_NO = #{empNo}
                AND KEY = #{key}
            """)
    int edit(ProjectVo vo, MemberVo memberVo);

    @Update("""
             UPDATE PROJECT
                 SET
                     DEL_YN = 'Y'
             WHERE KEY = #{key}
             AND WRITER_NO = #{writerNo}
            """)
    ProjectVo delete(ProjectVo vo);

}

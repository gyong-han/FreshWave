package com.semi.gam.project.mapper;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.vo.ProjectMemberVo;
import com.semi.gam.project.vo.ProjectVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
                 #{memberVo.id}
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
                    NAME = #{vo.name}
                    , PRIORITY = #{priority}
                    , ACCESS_LEVEL = #{accessLevel}
                    , START_DATE = {startDate}
                    , END_DATE = {endDate}
                    , MODIFY_DATE = SYSDATE
                WHERE DEL_YN = 'N'
                AND WRITER_NO = #{id}
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


    @Select("""
            SELECT
                    P.NAME
                    , P.KEY
                    , D.DEPT_NAME  AS deptName
                    , B.NAME       AS memberName
                    , R.NAME       AS priorityName
                    , P.START_DATE
                    , P.END_DATE
            FROM PROJECT P
            JOIN PRIORITY R ON (P.PRIORITY = R.NO)
            JOIN EMPLOYEE E ON (P.WRITER_NO = E.EMP_NO)
            JOIN DEPT D ON (E.DEPT_CODE = D.DEPT_CODE)
            JOIN MEMBER B ON (E.EMP_NO = B.NO)
            WHERE P.DEL_YN = 'N'
            AND P.WRITER_NO = #{id}
            ORDER BY P.ENROLL_DATE DESC
            """)
    List<ProjectVo> getProjectList(MemberVo loginMemberVo);


    @Select("""
            SELECT
                    P.NAME
                    , B.NAME          AS  memberName
                    , R.NAME        AS  priorityName
                    , P.START_DATE
                    , P.END_DATE
                    , S.PM_ACCESS   AS  accessName
            FROM PROJECT P
            JOIN EMPLOYEE E ON (P.WRITER_NO = E.EMP_NO)
            JOIN MEMBER B ON (E.EMP_NO = B.ID)
            JOIN PERMISSION S ON (P.ACCESS_LEVEL= S.NO)
            JOIN PRIORITY R ON (P.PRIORITY = R.NO)
            WHERE KEY = #{key}
            AND B.ID = #{loginMemberVo.id}
            """)
    ProjectVo getProject(String key, MemberVo loginMemberVo);


    @Select("""
            SELECT
                P.NAME
                , P.KEY
                , D.DEPT_NAME
                , B.NAME
                , R.NAME
                , P.START_DATE
                , P.END_DATE
            FROM PROJECT P
            JOIN PROJECT_MEMBER M ON (M.PRJ_KEY = P.KEY)
            JOIN EMPLOYEE E ON (E.EMP_NO = P.WRITER_NO)
            JOIN MEMBER B ON (B.ID = P.WRITER_NO)
            JOIN DEPT D ON (D.DEPT_CODE = E.DEPT_CODE)
            JOIN PRIORITY R ON (R.NO = P.PRIORITY)
            WHERE M.EMP_NO = #{id}
            """)
    List<ProjectVo> getProjectAddMemberVo(MemberVo loginMemberVo);

    @Select("""
            SELECT
                P.NAME
                , D.DEPT_NAME
                , B.NAME
                , R.NAME
                , P.START_DATE
                , P.END_DATE
            FROM PROJECT P
            JOIN PROJECT_MEMBER M ON (M.PRJ_KEY = P.KEY)
            JOIN EMPLOYEE E ON (E.EMP_NO = P.WRITER_NO)
            JOIN MEMBER B ON (B.ID = P.WRITER_NO)
            JOIN DEPT D ON (D.DEPT_CODE = E.DEPT_CODE)
            JOIN PRIORITY R ON (R.NO = P.PRIORITY)
            WHERE M.EMP_NO = #{loginMemberVo.id}
            """)
    ProjectVo getProject1(String key, MemberVo loginMemberVo);

    @Select("""
            SELECT\s
                P.NAME
                , D.DEPT_NAME
                , B.NAME
                , R.NAME
                , P.START_DATE
                , P.END_DATE
                , B.ID
            FROM PROJECT P
            JOIN PROJECT_MEMBER M ON (M.PRJ_KEY = P.KEY)
            JOIN EMPLOYEE E ON (E.EMP_NO = P.WRITER_NO)
            JOIN MEMBER B ON (B.ID = P.WRITER_NO)
            JOIN DEPT D ON (D.DEPT_CODE = E.DEPT_CODE)
            JOIN PRIORITY R ON (R.NO = P.PRIORITY)
            WHERE M.EMP_NO = #{id}
            """)
    ProjectVo getProjectList1(MemberVo loginMemberVo);

    @Select("""
            SELECT
                B.NAME
                , D.DEPT_NAME
                , J.JOB_NAME
                , S.PM_ACCESS
            FROM PROJECT_MEMBER M
            JOIN MEMBER B ON (B.ID = M.EMP_NO)
            JOIN EMPLOYEE E ON (E.EMP_NO = M.EMP_NO)
            JOIN DEPT D ON (D.DEPT_CODE = E.DEPT_CODE)
            JOIN JOB J ON (J.JOB_CODE = E.JOB_CODE)
            JOIN PROJECT P ON (P.KEY = M.PRJ_KEY)
            JOIN PERMISSION S ON (S.NO = P.ACCESS_LEVEL)
            WHERE B.ID = #{loginMemberVo.id}
            AND KEY = #{key}
            """)
    ProjectMemberVo getAddMember(String key, MemberVo loginMemberVo);
}
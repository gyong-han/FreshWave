package com.semi.gam.project.mapper;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.vo.PageVo;
import com.semi.gam.project.vo.ProjectMemberVo;
import com.semi.gam.project.vo.ProjectVo;
import org.apache.ibatis.annotations.Delete;
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
                , NAME
                , START_DATE
                , END_DATE
                , DISCLOSURE
            )
            VALUES
            (
                 #{memberVo.id}
                , #{vo.priority}
                , #{vo.name}
                , #{vo.startDate}
                , #{vo.endDate}
                , #{vo.disclosure}
            )
                
            """)
    int write(ProjectVo vo, MemberVo memberVo);


    @Update("""
            UPDATE PROJECT
                SET
                    PRIORITY = #{vo.priority}
                    , START_DATE = #{vo.startDate}
                    , END_DATE = #{vo.endDate}
                    , MODIFY_DATE = SYSDATE
                    , DISCLOSURE = #{vo.disclosure}
            WHERE KEY = #{vo.key}
            AND WRITER_NO = #{loginMemberVo.id}
            AND DEL_YN = 'N'
            """)
    int edit(ProjectVo vo, MemberVo loginMemberVo);

    @Update("""
             UPDATE PROJECT
                 SET
                     DEL_YN = 'Y'
             WHERE KEY = #{vo}
             AND WRITER_NO = #{loginMemberVo.id}
            """)
    int delete(String vo, MemberVo loginMemberVo);


    @Select("""
            SELECT
                    P.NAME
                    , P.KEY
                    , D.DEPT_NAME  AS deptName
                    , B.NAME       AS memberName
                    , R.NAME       AS priorityName
                    , P.START_DATE
                    , P.END_DATE
                    , E.EMP_NO     AS memberNo
            FROM PROJECT P
            JOIN PRIORITY R ON (P.PRIORITY = R.NO)
            JOIN EMPLOYEE E ON (P.WRITER_NO = E.EMP_NO)
            JOIN DEPT D ON (E.DEPT_CODE = D.DEPT_CODE)
            JOIN MEMBER B ON (E.EMP_NO = B.ID)
            WHERE P.DEL_YN = 'N'
            AND P.WRITER_NO = #{loginMemberVo.id}
            ORDER BY P.ENROLL_DATE DESC
            OFFSET #{pvo.offset} ROWS FETCH NEXT #{pvo.projectLimit} ROWS ONLY
            """)
    List<ProjectVo> getProjectList(MemberVo loginMemberVo, PageVo pvo);

    @Select("""
            SELECT
                    P.NAME
                    , P.KEY
                    , D.DEPT_NAME  AS deptName
                    , B.NAME       AS memberName
                    , R.NAME       AS priorityName
                    , P.START_DATE
                    , P.END_DATE
                    , E.EMP_NO     AS memberNo
            FROM PROJECT P
            JOIN PRIORITY R ON (P.PRIORITY = R.NO)
            JOIN EMPLOYEE E ON (P.WRITER_NO = E.EMP_NO)
            JOIN DEPT D ON (E.DEPT_CODE = D.DEPT_CODE)
            JOIN MEMBER B ON (E.EMP_NO = B.ID)
            WHERE P.DEL_YN = 'N'
            AND P.WRITER_NO = #{id}
            ORDER BY P.ENROLL_DATE DESC
            """)
    List<ProjectVo> getProjectCardList(MemberVo loginMemberVo);



    @Select("""
            SELECT
                    P.NAME
                    , B.NAME          AS  memberName
                    , R.NAME        AS  priorityName
                    , P.START_DATE
                    , P.END_DATE
                    , P.KEY
                    , P.DISCLOSURE
            FROM PROJECT P
            JOIN EMPLOYEE E ON (P.WRITER_NO = E.EMP_NO)
            JOIN MEMBER B ON (E.EMP_NO = B.ID)
            JOIN PRIORITY R ON (P.PRIORITY = R.NO)
            WHERE KEY = #{key}
            AND B.ID = #{loginMemberVo.id}
            AND P.DEL_YN = 'N'
            """)
    ProjectVo getProject(String key, MemberVo loginMemberVo);


    @Select("""
            SELECT
                P.NAME
                , P.KEY
                , D.DEPT_NAME   AS deptName
                , B.NAME        AS memberName
                , R.NAME        AS priorityName
                , P.START_DATE
                , P.END_DATE
                , E.EMP_NO      AS memberNo
            FROM PROJECT P
            JOIN PROJECT_MEMBER M ON (M.PRJ_KEY = P.KEY)
            JOIN EMPLOYEE E ON (E.EMP_NO = P.WRITER_NO)
            JOIN MEMBER B ON (B.ID = P.WRITER_NO)
            JOIN DEPT D ON (D.DEPT_CODE = E.DEPT_CODE)
            JOIN PRIORITY R ON (R.NO = P.PRIORITY)
            WHERE M.EMP_NO = #{loginMemberVo.id}
            AND P.DEL_YN = 'N'
            OFFSET #{pvo.offset} ROWS FETCH NEXT #{pvo.projectLimit} ROWS ONLY
            """)
    List<ProjectVo> getProjectAddMemberVo(MemberVo loginMemberVo, PageVo pvo);

    @Select("""
            SELECT
                P.NAME
                , P.KEY
                , D.DEPT_NAME   AS deptName
                , B.NAME        AS memberName
                , R.NAME        AS priorityName
                , P.START_DATE
                , P.END_DATE
                , E.EMP_NO      AS memberNo
            FROM PROJECT P
            JOIN PROJECT_MEMBER M ON (M.PRJ_KEY = P.KEY)
            JOIN EMPLOYEE E ON (E.EMP_NO = P.WRITER_NO)
            JOIN MEMBER B ON (B.ID = P.WRITER_NO)
            JOIN DEPT D ON (D.DEPT_CODE = E.DEPT_CODE)
            JOIN PRIORITY R ON (R.NO = P.PRIORITY)
            WHERE M.EMP_NO = #{id}
            AND P.DEL_YN = 'N'
            """)
    List<ProjectVo> getProjectCardAddMemberVo(MemberVo loginMemberVo);


    @Select("""
            SELECT
                B.NAME
                , D.DEPT_NAME
                , J.JOB_NAME
                , M.MODI_AUTH
                , E.EMP_NO
                , B.PROFILE     AS  profileName
            FROM PROJECT_MEMBER M
            JOIN MEMBER B ON (B.ID = M.EMP_NO)
            JOIN EMPLOYEE E ON (E.EMP_NO = M.EMP_NO)
            JOIN DEPT D ON (D.DEPT_CODE = E.DEPT_CODE)
            JOIN JOB J ON (J.JOB_CODE = E.JOB_CODE)
            JOIN PROJECT P ON (P.KEY = M.PRJ_KEY)
            WHERE KEY = #{key}
            AND P.DEL_YN = 'N'
            """)
    List<ProjectMemberVo> getAddMember(String key);


    @Update("""
            UPDATE PROJECT_MEMBER
                SET
                    MODI_AUTH = '수정'
            WHERE EMP_NO = #{s}
            AND PRJ_KEY = #{vo.key}
            """)
    void addMemberEdit(String s, String access, ProjectVo vo);


    @Update("""
            UPDATE PROJECT_MEMBER
                SET
                    MODI_AUTH = '읽기'
            WHERE EMP_NO = #{s}
            AND PRJ_KEY = #{vo.key}
            """)
    void addMemberRead(String s, String access, ProjectVo vo);


    @Delete("""
            DELETE PROJECT_MEMBER
            WHERE PRJ_KEY = #{key}
            AND EMP_NO = #{empNo}
            """)
    int deleteMember(String empNo, String key);


    @Select("""
            SELECT
                M.PROFILE   AS  profileName
                , M.NAME    AS  memberName
                , D.DEPT_NAME
                , J.JOB_NAME
                , E.EMP_NO
            FROM MEMBER M
            JOIN EMPLOYEE E ON (E.EMP_NO = M.ID)
            JOIN JOB J ON (J.JOB_CODE = E.JOB_CODE)
            JOIN DEPT D ON (D.DEPT_CODE = E.DEPT_CODE)
            """)
    List<ProjectMemberVo> getAddMemberVo();

    @Select("""
            SELECT
                M.PROFILE   AS  profileName
                , M.NAME    AS  memberName
                , D.DEPT_NAME
                , J.JOB_NAME
                , E.EMP_NO
            FROM MEMBER M
            JOIN EMPLOYEE E ON (E.EMP_NO = M.ID)
            JOIN JOB J ON (J.JOB_CODE = E.JOB_CODE)
            JOIN DEPT D ON (D.DEPT_CODE = E.DEPT_CODE)
            WHERE E.EMP_NO = #{empNo}
            """)
    ProjectMemberVo addMemberVp(ProjectMemberVo empNo);

    @Select("""
            SELECT KEY
            FROM PROJECT
            WHERE NAME = #{vo.name}
            AND WRITER_NO = #{loginMemberVo.id}
            """)
    String getProjectKey(ProjectVo vo, MemberVo loginMemberVo);

    @Insert("""
            INSERT INTO PROJECT_MEMBER
            (
                NO
                , PRJ_KEY
                , EMP_NO
                , MODI_AUTH
            )
             VALUES
             (
                 SEQ_PROJECT_MEMBER.NEXTVAL
                 , #{prjKey}
                 , #{s}
                 , #{s1}
             )
            """)
    int setAddMember(String s, String s1, String prjKey);

    @Select("""
            SELECT COUNT(*)
            FROM PROJECT
            WHERE WRITER_NO = #{id}
            AND DEL_YN  = 'N'
            """)
    int getProjectListCnt(MemberVo loginMemberVo);

    @Select("""
            SELECT COUNT(*)
            FROM PROJECT_MEMBER M
            WHERE EMP_NO = #{id}
            """)
    int getProjectAddListCnt(MemberVo loginMemberVo);
}
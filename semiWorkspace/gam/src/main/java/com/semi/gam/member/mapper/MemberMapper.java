package com.semi.gam.member.mapper;

import com.semi.gam.admin.vo.AdminVo;
import com.semi.gam.dept.vo.DeptVo;
import com.semi.gam.employee.vo.EmployeeVo;
import com.semi.gam.job.vo.JobVo;
import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.util.page.PageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MemberMapper {

    @Select("""
            SELECT ID , NAME
            FROM MEMBER
            """)
    List<MemberVo> getEmpVo();

    @Select("""
            SELECT
                E.CP_CODE
                ,M.ID
                ,M.PWD
                ,M.NAME
                ,M.NICK
                ,M.ID_NUM
                ,M.GENDER
                ,M.PHONE
                ,M.EMAIL
                ,M.ADDRESS
                ,M.PROFILE
                ,E.JOB_CODE
                ,E.DEPT_CODE
                ,E.START_TIME
                ,E.FINISH_DATE
                ,E.HIRE_DATE
                ,E.QUIT_DATE
                ,E.QUIT_YN
                ,D.DEPT_NAME
                ,J.JOB_NAME
            FROM MEMBER M
            LEFT OUTER JOIN EMPLOYEE E ON (E.EMP_NO = M.ID)
            LEFT OUTER JOIN DEPT D ON (D.DEPT_CODE = E.DEPT_CODE)
            LEFT OUTER JOIN JOB J ON (J.JOB_CODE = E.JOB_CODE)
            WHERE E.QUIT_YN = 'N'
            AND E.CP_CODE = #{cpCode}
            AND M.ID = ${id}
            AND M.PWD = #{pwd}
            """)
    MemberVo loginMember(MemberVo mvo);

    @Select("""
            SELECT
                CP_CODE
                ,ID
                ,PWD
            FROM ADMIN
            WHERE CP_CODE = #{cpCode}
            AND ID = #{id}
            AND PWD = #{pwd}
            """)
    AdminVo loginAdmin(AdminVo avo);

    @Insert("""
            INSERT INTO MEMBER
            (
                NO,
                ID,
                NAME,
                NICK,
                PWD,
                ID_NUM,
                GENDER,
                PHONE,
                EMAIL,
                ADDRESS
            )
            VALUES
            (
                SEQ_MEMBER.NEXTVAL,
                (SELECT MAX(EMP_NO) FROM EMPLOYEE),
                #{name},
                #{nick},
                #{pwd},
                #{idNum},
                #{gender},
                #{phone},
                #{email},
                #{address}
            )
            """)
    int join(MemberVo mvo);

    @Insert("""
            INSERT INTO EMPLOYEE
                (
                    CP_CODE,
                    JOB_CODE,
                    DEPT_CODE
                )
                VALUES
                (
                    'A001',
                    #{jobCode},
                    #{deptCode}
                )
            """)
    int Company(EmployeeVo evo);

    @Select("""
            SELECT *
            FROM JOB
            """)
    List<JobVo> getJobVoList();


    @Select("""
            SELECT *
            FROM DEPT
            """)
    List<DeptVo> getDeptVoList();

    @Update("""
            UPDATE EMPLOYEE
                SET START_TIME = SYSDATE
            WHERE QUIT_YN = 'N'
            """)
    int stratlogin(EmployeeVo evo);


    int edit(MemberVo vo);


    @Select("""
            SELECT
                M.NAME
                , M.PROFILE
                , D.DEPT_NAME
                , J.JOB_NAME
                , M.NICK
                , M.PWD
                , M. PHONE
                , M. ADDRESS
            FROM MEMBER M
            LEFT OUTER JOIN EMPLOYEE E ON (E.EMP_NO = M.ID)
            LEFT OUTER JOIN DEPT D ON (D.DEPT_CODE = E.DEPT_CODE)
            LEFT OUTER JOIN JOB J ON (J.JOB_CODE = E.JOB_CODE)
            WHERE M.ID = #{id}
            """)
    MemberVo getMember(MemberVo vo);

    @Select("""
            SELECT *
            FROM MEMBER
            WHERE NICK = #{nick}
            """)
    MemberVo checkDupNick(String nick);

    @Update("""
            UPDATE EMPLOYEE
                SET
                    FINISH_DATE = SYSDATE
            WHERE QUIT_YN = 'N'
            AND EMP_NO = ${id}
            """)
    int logOutFinish(MemberVo loginMemberVo);

    int getMemberCnt(String searchType, String searchValue);

    List<EmployeeVo> getMemberList(PageVo pvo, String searchType, String searchValue);

}

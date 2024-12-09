package com.semi.gam.admin.mapper;

import com.semi.gam.dept.vo.DeptVo;
import com.semi.gam.employee.vo.EmployeeVo;
import com.semi.gam.job.vo.JobVo;
import com.semi.gam.util.page.PageVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminMapper {


    int getMemberCnt(String searchType, String searchValue);

    List<EmployeeVo> getMemberList(PageVo pvo, String searchType, String searchValue);

    @Select("""
            SELECT
                M.NAME      AS MEMBER_NAME
                , M.PROFILE
                , M.GENDER
                , E.EMP_NO
                , D.DEPT_NAME
                , D.DEPT_CODE
                , J.JOB_NAME
                , J.JOB_CODE
                , M.PHONE
                , M.EMAIL
                , M.NICK
                , SUBSTR(M.ID_NUM, 1, 6)        AS ID_NUM
                , SUBSTR(E.HIRE_DATE, 1, 10)     AS HIRE_DATE
                , M.ADDRESS
                , M.PWD
            FROM EMPLOYEE E
            LEFT OUTER JOIN MEMBER M ON (M.ID = E.EMP_NO)
            LEFT OUTER JOIN DEPT D ON(D.DEPT_CODE = E.DEPT_CODE)
            LEFT OUTER JOIN JOB J ON(J.JOB_CODE = E.JOB_CODE)
            WHERE E.EMP_NO = #{no}
            AND E.QUIT_YN = 'N'
            """)
    EmployeeVo getMember(String no);

    @Update("""
            UPDATE EMPLOYEE
                SET
                    JOB_CODE = #{jobCode}
                    , DEPT_CODE = #{deptCode}
                    , HIRE_DATE = #{hireDate}
            WHERE EMP_NO = #{no}
            AND QUIT_YN = 'N'
            """)
    int edit(EmployeeVo vo);


    @Update("""
            UPDATE EMPLOYEE
                SET
                    QUIT_YN = 'Y'
                    , QUIT_DATE = SYSDATE
            WHERE EMP_NO = #{no}
            AND QUIT_YN = 'N'
            """)
    int del(String no);

    List<EmployeeVo> getMemberListDel(PageVo pvo, String searchType, String searchValue);

    int getMemberCntDel(String searchType, String searchValue);

    @Select("""
            SELECT
                M.NAME      AS MEMBER_NAME
                , M.PROFILE
                , M.GENDER
                , E.EMP_NO
                , D.DEPT_NAME
                , D.DEPT_CODE
                , J.JOB_NAME
                , J.JOB_CODE
                , M.PHONE
                , M.EMAIL
                , M.NICK
                , E.QUIT_DATE
                , SUBSTR(M.ID_NUM, 1, 6)        AS ID_NUM
                , SUBSTR(E.HIRE_DATE, 1, 8)     AS HIRE_DATE
                , M.ADDRESS
                , M.PWD
            FROM EMPLOYEE E
            LEFT OUTER JOIN MEMBER M ON (M.ID = E.EMP_NO)
            LEFT OUTER JOIN DEPT D ON(D.DEPT_CODE = E.DEPT_CODE)
            LEFT OUTER JOIN JOB J ON(J.JOB_CODE = E.JOB_CODE)
            WHERE E.EMP_NO = #{no}
            AND E.QUIT_YN = 'Y'
            """)
    EmployeeVo getMemberDel(String no);

    @Delete("""
            DELETE FROM EMPLOYEE
            WHERE EMP_NO = #{no}
            AND QUIT_YN = 'Y'
            """)
    int out(String no);

    @Select("""
            SELECT
                M.NAME
                ,D.DEPT_NAME
                ,J.JOB_NAME
            FROM EMPLOYEE E
            LEFT OUTER JOIN MEMBER M ON (M.ID = E.EMP_NO)
            LEFT OUTER JOIN DEPT D ON(D.DEPT_CODE = E.DEPT_CODE)
            LEFT OUTER JOIN JOB J ON(J.JOB_CODE = E.JOB_CODE)
            WHERE QUIT_YN = 'N'
            """)
    List<EmployeeVo> getMemberHomeList();

}

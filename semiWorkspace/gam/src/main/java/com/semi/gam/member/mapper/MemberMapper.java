package com.semi.gam.member.mapper;

<<<<<<< HEAD
import com.semi.gam.member.vo.MemberVo;
=======
import com.semi.gam.admin.vo.AdminVo;
import com.semi.gam.dept.vo.DeptVo;
import com.semi.gam.employee.vo.EmployeeVo;
import com.semi.gam.job.vo.JobVo;
import com.semi.gam.member.vo.MemberVo;
import org.apache.ibatis.annotations.Insert;
>>>>>>> 5e412a01822d795dc01dfb9bae757f28dc21d6b5
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

<<<<<<< HEAD

    @Select("""
            SELECT EMP_NO , NAME
            FROM MEMBER
            """)
    List<MemberVo> getEmpVo();
}
=======
    @Select("""
            SELECT
                E.CP_CODE
                ,M.ID
                ,M.PWD
            FROM EMPLOYEE E
            LEFT OUTER JOIN MEMBER M ON (M.ID = E.EMP_NO)
            WHERE E.QUIT_YN = 'N'
            AND CP_CODE = #{cpCode}
            AND ID = #{id}
            AND PWD = #{pwd}
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
                NO
                , ID
                , NAME
                , PWD
                , ID_NUM
                , GENDER
                , PHONE
                , EMAIL
                , ADDRESS
                , ID_NUM
            )
            VALUES
            (
                SEQ_MEMBER.NEXTVAL
                ,#{id}
                ,#{name}
                ,#{pwd}
                ,#{idNum}
                ,#{gender}
                ,#{phone}
                ,#{email}
                ,#{address}
                ,#{idNsum}
            )
            """)
    int join(MemberVo mvo);

    @Insert("""
            INSERT INTO EMPLOYEE
            (
                EMP_NO
                ,CP_CODE
                ,JOB_CODE
                ,DEPT_CODE
            )
            VALUES
            (
                 '2401' || LPAD(SEQ_EMPLOYEE.NEXTVAL, 2, '0')
                 ,'A001'
                 ,#{jobCode}
                 ,#{deptCode}
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
}
>>>>>>> 5e412a01822d795dc01dfb9bae757f28dc21d6b5

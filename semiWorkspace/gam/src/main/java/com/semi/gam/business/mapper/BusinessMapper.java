package com.semi.gam.business.mapper;

import com.semi.gam.business.vo.BtCodeVo;
import com.semi.gam.business.vo.BusinessVo;
import com.semi.gam.business.vo.RankVo;
import com.semi.gam.util.page.PageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BusinessMapper {


    @Insert("""
            INSERT INTO BUSINESS (NO,MANAGER_NO,DEPT_NAME,NAME,CEO,BRN,BT_CODE,ADDRESS,PHONE)
            VALUES(SEQ_BUSINESS.NEXTVAL,#{managerNo},#{deptName},#{name},#{ceo},#{brn},#{btCode},#{address},#{phone})
            """)
    int insert(BusinessVo vo);

    @Insert("""
            INSERT INTO BUSINESS_HISTORY(NO,BP_NO,START_DATE,END_DATE)
            VALUES (SEQ_BUSINESS_HISTORY.NEXTVAL,SEQ_BUSINESS.CURRVAL,#{startDate},#{endDate})
            """)
    int insertHistory(BusinessVo vo);

    @Insert("""
            INSERT INTO BP_ATTACHMENT (NO,REF_BP_NO,CHANGE_NAME,ORIGIN_NAME)
            VALUES ((SELECT GET_BP_ATTACHMENT_NO FROM DUAL),SEQ_BUSINESS.CURRVAL,#{changeName},#{originName})
            """)
    int insertAttachment(String changeName, String originName);

    List<BusinessVo> getBusinessVoList(PageVo pvo, String searchType, String searchValue);


    @Select("""
            SELECT B.NO,B.MANAGER_NO,B.BRN,B.BT_CODE,B.ADDRESS,B.DEL_YN,B.NAME,B.CEO,B.PHONE,M.NAME AS MANAGER_NAME,B.DEPT_NAME,
            H.START_DATE,H.END_DATE,B.ENROLL_DATE,B.MODIFY_DATE,H.BP_NO,A.ORIGIN_NAME,A.CHANGE_NAME
            FROM BUSINESS B
            JOIN MEMBER M ON(B.MANAGER_NO = M.ID)
            LEFT JOIN BUSINESS_HISTORY H ON(B.NO = H.BP_NO)
            LEFT JOIN BP_ATTACHMENT A ON(B.NO = A.REF_BP_NO)
            WHERE B.NO = ${no}
            AND DEL_YN = 'N'
            """)
    BusinessVo detail(String no);

    @Select("""
            SELECT NAME, COUNT(*) AS TRANSACTIONNUM, RANK() OVER(ORDER BY COUNT(*) DESC) AS RANK
            FROM(
                SELECT B.NAME, B.DEPT_NAME
                    FROM BUSINESS B
                    JOIN BUSINESS_HISTORY H ON (B.NO = H.BP_NO)
                    WHERE B.DEL_YN = 'N'
            )SUB
            GROUP BY NAME
            ORDER BY COUNT(*) DESC
            FETCH FIRST 5 ROWS ONLY
            """)
    List<RankVo> getDataList();

    int getBusinessCnt(String searchValue, String searchType);

    @Update("""
            UPDATE BUSINESS
            SET DEL_YN = 'Y'
            WHERE NO = #{no}
            """)
    int delete(String no);

    @Update("""
            UPDATE BUSINESS
            SET CEO = #{ceo},
            ADDRESS = #{address},
            PHONE = #{phone},
            MODIFY_DATE = SYSDATE
            WHERE NO = #{no}
            """)
    int edit(BusinessVo vo);

    @Update("""
            UPDATE BUSINESS_HISTORY
            SET START_DATE = #{startDate},
            END_DATE = #{endDate}
            WHERE BP_NO = #{no}
            """)
    int editHistory(BusinessVo vo);

    @Select("SELECT NO,CODE,NAME FROM BT_CODE")
    List<BtCodeVo> getBtCodeList();

    @Update("""
            UPDATE BP_ATTACHMENT
            SET CHANGE_NAME = #{changeName},
            ORIGIN_NAME = #{originName}
            WHERE REF_BP_NO = #{vo.no}
            """)
    int editAttachment(BusinessVo vo, String originName, String changeName);
}

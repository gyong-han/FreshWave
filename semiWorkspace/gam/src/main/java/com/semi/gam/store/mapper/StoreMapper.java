package com.semi.gam.store.mapper;

import com.semi.gam.store.vo.StatusVo;
import com.semi.gam.store.vo.StoreVo;
import com.semi.gam.util.page.PageVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StoreMapper {

    @Insert("""
            INSERT INTO STORE (NO,MANAGER_NO,NAME,CEO,BRN,START_DATE,END_DATE,ADDRESS,PHONE,CEO_PHONE,ENROLL_DATE,EDU_DATE,OPEN_DATE,STATUS)
            VALUES(SEQ_STORE.NEXTVAL,#{managerNo},#{name},#{ceo},#{brn},#{startDate},#{endDate},#{address},#{phone},#{ceoPhone},SYSDATE,#{eduDate},#{openDate},#{status})
            """)
    int insert(StoreVo vo);


    @Insert("""
            INSERT INTO ST_ATTACHMENT (NO,REF_ST_NO,CHANGE_NAME,ORIGIN_NAME) 
            VALUES ((SELECT GET_ST_ATTACHMENT_NO FROM DUAL),SEQ_STORE.CURRVAL,#{changeName},#{originName})
            """)
    int insertAttachment(String changeName, String originName);

    List<StoreVo> getStoreVoList(PageVo pvo, String searchType, String searchValue);

    @Select("""
            SELECT S.NO,S.MANAGER_NO,S.NAME,S.CEO,S.PHONE,S.CEO_PHONE,S.BRN,S.EDU_DATE,S.OPEN_DATE,S.CLOSE_DATE,S.STATUS,S.START_DATE,S.END_DATE,
            S.ADDRESS,A.ORIGIN_NAME,S.ENROLL_DATE,A.CHANGE_NAME
            FROM STORE S
            LEFT JOIN ST_ATTACHMENT A ON(S.NO = A.REF_ST_NO)
            WHERE DEL_YN = 'N'
            AND S.NO = ${no}
            """)
    StoreVo detail(String no);

    int getStoreCnt(String searchValue, String searchType);

    @Update("""
            UPDATE STORE
            SET DEL_YN = 'Y'
            WHERE NO = #{bno}
            """)
    int delete(String bno);


    @Update("""
            UPDATE STORE
            SET CEO = #{ceo},
            START_DATE = #{startDate},
            END_DATE = #{endDate},
            ADDRESS = #{address},
            PHONE = #{phone},
            CEO_PHONE = #{ceoPhone},
            MODIFY_DATE = SYSDATE,
            EDU_DATE = #{eduDate},
            OPEN_DATE = #{openDate},
            STATUS = #{status},
            CLOSE_DATE = #{closeDate}
            WHERE NO = #{no}
            """)
    int edit(StoreVo svo);

    @Select("""
            SELECT STATUS,COUNT(*) AS "COUNT"
            FROM(
                SELECT STATUS
                FROM STORE
                WHERE DEL_YN = 'N'
            )SUB
            GROUP BY STATUS
            ORDER BY COUNT(*)DESC
            """)
    List<StatusVo> storeData();

    @Update("""
            UPDATE ST_ATTACHMENT
            SET CHANGE_NAME = #{changeName},
            ORIGIN_NAME = #{originName}
            WHERE REF_ST_NO = #{svo.no}
            """)
    int editAttachment(StoreVo svo, String originName, String changeName);
}
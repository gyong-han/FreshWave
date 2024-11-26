package com.semi.gam.store.mapper;

import com.semi.gam.store.vo.StoreVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StoreMapper {

    @Insert("""
            INSERT INTO STORE (NO,MANAGER_NO,NAME,CEO,BRN,START_DATE,END_DATE,ADDRESS,PHONE,CEO_PHONE,ENROLL_DATE,EDU_DATE,OPEN_DATE,STATUS)
            VALUES(SEQ_STORE.NEXTVAL,'1',#{name},#{ceo},#{brn},#{startDate},#{endDate},#{address},#{phone},#{ceoPhone},SYSDATE,#{eduDate},#{openDate},#{status})
            """)
    int insert(StoreVo vo);


    @Insert("""
            INSERT INTO ST_ATTACHMENT (NO,REF_ST_NO,CHANGE_NAME,ORIGIN_NAME) 
            VALUES ((SELECT GET_ST_ATTACHMENT_NO FROM DUAL),SEQ_STORE.CURRVAL,#{changeName},#{originName})
            """)
    int insertAttachment(String changeName, String originName);

    @Select("""
            SELECT NO,NAME,CEO,PHONE,STATUS,OPEN_DATE,START_DATE,END_DATE
            FROM STORE
            WHERE DEL_YN = 'N'
            ORDER BY NO DESC
            """)
    List<StoreVo> getStoreVoList();

    @Select("""
            SELECT S.NAME,S.CEO,S.PHONE,S.CEO_PHONE,S.BRN,S.EDU_DATE,S.OPEN_DATE,S.CLOSE_DATE,S.STATUS,S.START_DATE,S.END_DATE,
            S.ADDRESS,A.ORIGIN_NAME
            FROM STORE S
            LEFT JOIN ST_ATTACHMENT A ON(S.NO = A.REF_ST_NO)
            WHERE DEL_YN = 'N'
            AND S.NO = ${bno}
            """)
    StoreVo detail(String bno);
}
package com.semi.gam.store.mapper;

import com.semi.gam.store.vo.StoreVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    @Insert("""
            INSERT INTO STORE (NO,MANAGER_NO,NAME,CEO,BRN,START_DATE,END_DATE,ADDRESS,PHONE,CEO_PHONE,ENROLL_DATE,EDU_DATE,OPEN_DATE,STATUS)
            VALUES(SEQ_STORE.NEXTVAL,'1',#{name},#{ceo},#{brn},#{startDate},#{endDate},#{address},#{phone},#{ceoPhone},SYSDATE,#{eduDate},#{openDate},#{status})
            """)
    int insert(StoreVo vo);


    @Insert("""
            INSERT INTO ST_ATTACHMENT (NO,REF_ST_NO,CHANGE_NAME) VALUES ((SELECT GET_ST_ATTACHMENT_NO FROM DUAL),SEQ_STORE.CURRVAL,#{x})
            """)
    int insertAttachment(String changeName);
}
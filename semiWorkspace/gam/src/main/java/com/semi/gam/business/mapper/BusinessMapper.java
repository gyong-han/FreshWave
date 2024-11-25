package com.semi.gam.business.mapper;

import com.semi.gam.business.vo.BusinessVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessMapper {


    @Insert("""
            INSERT INTO BUSINESS (NO,MANAGER_NO,DEPT_NAME,NAME,CEO,BRN,BT_CODE,ADDRESS,PHONE)
            VALUES(SEQ_BUSINESS.NEXTVAL,'1',#{deptName},#{name},#{ceo},#{brn},#{btCode},#{address},#{phone})
            """)
    int insert(BusinessVo vo);

    @Insert("""
            INSERT INTO BUSINESS_HISTORY(NO,BP_NO,START_DATE,END_DATE)
            VALUES (SEQ_BUSINESS_HISTORY.NEXTVAL,SEQ_BUSINESS.CURRVAL,#{startDate},#{endDate})
            """)
    int insertHistory(BusinessVo vo);

    @Insert("""
            INSERT INTO BP_ATTACHMENT (NO,REF_BP_NO,CHANGE_NAME)
            VALUES ((SELECT GET_BP_ATTACHMENT_NO FROM DUAL),SEQ_BUSINESS.CURRVAL,'changeName')
            """)
    int insertAttachment(String changeName);
}

package com.semi.gam.phoneReservation.mapper;

import com.semi.gam.phoneReservation.vo.PhoneReservationVo;
import com.semi.gam.util.page.PageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PhoneReservationMapper {

    @Insert("""
            INSERT INTO PHONE_RESERVATION
            (
                NO
                , WRITER_NO
                , CP_CODE
                , RDATE
                , RTIME
                , PHONE
                , TITLE
                , CONTENT
            )
            VALUES
            (
                SEQ_PHONE_RESERVATION.NEXTVAL
                , #{writerNo}
                , #{cpCode}
                , #{rDate}
                , #{rTime}
                , #{phone}
                , #{title}
                , #{content}
            )
            """)
    int write(PhoneReservationVo vo);

    int edit(PhoneReservationVo vo);

    int getReservationCnt(String searchType, String searchValue);

    @Select("""
            SELECT
                P.NO
                , P.WRITER_NO
                , M.NAME AS WRITER_NAME
                , P.CP_CODE
                , P.RDATE
                , P.RTIME
                , P.PHONE
                , P.TITLE
                , P.CONTENT
                , P.ENROLL_DATE
                , P.MODIFY_DATE
                , P.DEL_YN
            FROM PHONE_RESERVATION P
            JOIN MEMBER M ON (P.WRITER_NO = M.ID)
            WHERE P.DEL_YN = 'N'
            ORDER BY P.NO DESC
            """)
    PhoneReservationVo getReservation();

    List<PhoneReservationVo> getReservationVoList(PageVo pvo, String searchType, String searchValue);
}

package com.semi.gam.phoneReservation.mapper;

import com.semi.gam.phoneReservation.vo.PhoneReservationVo;
import com.semi.gam.util.page.PageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
                , #{rdate}
                , #{rtime}
                , #{phone}
                , #{title}
                , #{content}
            )
            """)
    int write(PhoneReservationVo vo);

    @Update("""
            UPDATE PHONE_RESERVATION
                SET
                    MODIFY_DATE = SYSDATE
                    , CP_CODE = #{vo.cpCode}
                    , RDATE = #{vo.rdate}
                    , RTIME = #{vo.rtime}
                    , PHONE = #{vo.phone}
                    , TITLE = #{vo.title}
                    , CONTENT = #{vo.content}
            WHERE NO = #{rno}
                AND DEL_YN = 'N'
            """)
    int edit(PhoneReservationVo vo, String rno);

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
            WHERE P.NO = ${rno}
                AND P.WRITER_NO = ${id}
                AND P.DEL_YN = 'N'
            ORDER BY P.NO DESC
            """)
    PhoneReservationVo getReservationByNo(String rno, String id);

    @Update("""
            UPDATE PHONE_RESERVATION
                SET
                    DEL_YN = 'Y'
            WHERE NO = ${rno}
                AND WRITER_NO = ${vo.writerNo}
            """)
    int del(String rno, PhoneReservationVo vo);
}

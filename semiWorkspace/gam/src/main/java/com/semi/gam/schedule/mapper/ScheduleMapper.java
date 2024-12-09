package com.semi.gam.schedule.mapper;

import com.semi.gam.schedule.vo.PriorityVo;
import com.semi.gam.schedule.vo.ScheduleVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ScheduleMapper {

    @Select("""
            SELECT
                S.NO
                , S.WRITER_NO
                , S.PRIORITY
                , P.NAME    AS PRIORITY_NAME
                , S.TITLE
                , S.CONTENT
                , S.LOCATION
                , S.START_DATE
                , S.FINISH_DATE
                , S.START_TIME
                , S.FINISH_TIME
                , S.ALLDAY
                , S.SHARE_YN
                , S.DEL_YN
            FROM SCHEDULE S
            JOIN PRIORITY P ON (S.PRIORITY = P.NO)
            WHERE WRITER_NO = #{writerNo}
                AND S.SHARE_YN = 'N'
                AND S.DEL_YN = 'N'
            """)
    List<ScheduleVo> getSchVoList(ScheduleVo vo);

    @Insert("""
            INSERT INTO SCHEDULE
            (
                NO
                , WRITER_NO
                , PRIORITY
                , TITLE
                , CONTENT
                , LOCATION
                , START_DATE
                , FINISH_DATE
                , START_TIME
                , FINISH_TIME
            )
            VALUES
            (
                SEQ_SCHEDULE.NEXTVAL
                , #{writerNo}
                , #{priority}
                , #{title}
                , #{content}
                , #{location}
                , #{startDate}
                , #{finishDate}
                , #{startTime}
                , #{finishTime}
            )
            """)
    int writePersonal(ScheduleVo vo);

    @Update("""
            UPDATE SCHEDULE
                SET
                    TITLE = #{vo.title}
                    , CONTENT = #{vo.content}
                    , START_DATE = #{vo.startDate}
                    , FINISH_DATE = #{vo.finishDate}
                    , START_TIME = #{vo.startTime}
                    , FINISH_TIME = #{vo.finishTime}
                    , LOCATION = #{vo.location}
                    , PRIORITY = #{vo.priority}
                WHERE NO = #{sno}
                AND SHARE_YN = 'N'
                AND DEL_YN = 'N'
            """)
    int edit(ScheduleVo vo, String sno);

    @Update("""
            UPDATE SCHEDULE
                SET
                    DEL_YN = 'Y'
            WHERE WRITER_NO = ${vo.writerNo}
                AND NO = ${sno}
            """)
    int del(String sno, ScheduleVo vo);

    @Select("""
            SELECT
                S.NO
                , WRITER_NO
                , PRIORITY
                , P.NAME    AS PRIORITY_NAME
                , TITLE
                , CONTENT
                , LOCATION
                , START_DATE
                , FINISH_DATE
                , START_TIME
                , FINISH_TIME
                , ALLDAY
                , SHARE_YN
                , DEL_YN
            FROM SCHEDULE S
            JOIN PRIORITY P ON (S.PRIORITY = P.NO)
            WHERE WRITER_NO = ${writerNo}
                AND S.NO = ${sno}
                AND S.SHARE_YN = 'N'
                AND S.DEL_YN = 'N'
            """)
    ScheduleVo getEventByNo(String sno, String writerNo);

    @Select("""
            SELECT
                NO
                , NAME
            FROM PRIORITY
            """)
    List<PriorityVo> getPriorityVo();
}

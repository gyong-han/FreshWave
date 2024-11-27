package com.semi.gam.schedule.mapper;

import com.semi.gam.schedule.vo.ScheduleVo;
import org.apache.ibatis.annotations.Insert;

public interface ScheduleMapper {
    @Insert("""
            INSERT INTO SCHEDULE
            (
                NO
                , WRITER_NO
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
                , 1
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

    @Insert("""
            INSERT INTO SCHEDULE
            (
                NO
                , WRITER_NO
                , TITLE
                , CONTENT
                , LOCATION
                , START_DATE
                , FINISH_DATE
                , START_TIME
                , FINISH_TIME
                , SHARE_YN
            )
            VALUES
            (
                SEQ_SCHEDULE.NEXTVAL
                , 1
                , #{title}
                , #{content}
                , #{location}
                , #{startDate}
                , #{finishDate}
                , #{startTime}
                , #{finishTime}
                , 'Y'
            )
            """)
    int writeShare(ScheduleVo vo);

    @Insert("""
            
            """)
    int addUser(ScheduleVo vo);
}

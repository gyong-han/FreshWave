package com.semi.gam.home.mapper;

import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.project.vo.PriorityVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HomeMapper {

    @Select("""
            SELECT NO, TITLE, TO_CHAR(ENROLL_DATE, 'YYYY.MM.DD') AS ENROLL_DATE
            FROM NOTICE
            WHERE URGENT_YN = 'Y'
            ORDER BY ENROLL_DATE DESC
            FETCH FIRST 4 ROWS ONLY
            """)
    List<NoticeVo> getRedNoticeList();

    @Select("""
            SELECT NO, TITLE, TO_CHAR(ENROLL_DATE, 'YYYY.MM.DD') AS ENROLL_DATE
            FROM NOTICE
            WHERE URGENT_YN = 'N'
            ORDER BY ENROLL_DATE DESC
            FETCH FIRST 4 ROWS ONLY
            """)
    List<NoticeVo> getBlueNoticeList();

    @Select("""
                SELECT PRIORITY,COUNT(*) AS "COUNT"
                FROM(
                    SELECT R.NAME AS PRIORITY
                    FROM PROJECT P
                    JOIN PRIORITY R ON(P.PRIORITY = R.NO)
                    WHERE P.WRITER_NO = ${id}
                )SUB
                GROUP BY PRIORITY
            """)
    List<PriorityVo> getPriorityList1(String id);

    @Select("""
            SELECT PRIORITY,COUNT(*) AS "COUNT"
            FROM(
                SELECT R.NAME AS PRIORITY
                FROM PROJECT P
                LEFT JOIN PROJECT_MEMBER M ON (P.KEY = M.PRJ_KEY)
                JOIN PRIORITY R ON(P.PRIORITY = R.NO)
                WHERE M.EMP_NO = ${id}
            )SUB
            GROUP BY PRIORITY
            """)
    List<PriorityVo> getPriorityList2(String id);
}

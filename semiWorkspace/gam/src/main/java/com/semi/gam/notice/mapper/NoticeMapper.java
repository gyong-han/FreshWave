package com.semi.gam.notice.mapper;

import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.page.PageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Insert("""
            INSERT INTO NOTICE
            (   NO
                , WRITER_NO
                , TITLE
                , CONTENT
                , URGENT_YN
                , HIT
                , ENROLL_DATE
                , DEL_YN
                )
            VALUES
            (   SEQ_NOTICE.NEXTVAL
                , #{writerNo}
                , #{title}
                , #{content}
                , #{urgentYn}
                , 0
                , SYSDATE
                , 'N'
            )
            """)
    int write(NoticeVo vo);

    @Select("""
            SELECT COUNT(*)
            FROM NOTICE
            WHERE DEL_YN = 'N'
            """)
    int getNoticeCnt();

    @Select("""
            SELECT
                  N.NO
                , N.WRITER_NO
                , N.TITLE
                , N.CONTENT
                , N.URGENT_YN
                , N.HIT
                , N.ENROLL_DATE
                , N.DEL_YN
                , D.DEPT_NAME
                , M.NAME
                FROM NOTICE N
                JOIN EMPLOYEE E ON N.WRITER_NO = E.EMP_NO
                JOIN DEPT D ON E.DEPT_CODE = D.DEPT_CODE
                JOIN MEMBER M ON E.EMP_NO = M.ID
                WHERE N.DEL_YN = 'N'
                ${str}
                ORDER BY N.URGENT_YN DESC
                ,N.ENROLL_DATE DESC
                OFFSET #{pvo.offset} ROWS FETCH NEXT #{pvo.boardLimit} ROWS ONLY
            """)
    List<NoticeVo> getNoticeList(PageVo pvo , String str);

    @Select("""
            SELECT DISTINCT
                N.NO ,
                N.TITLE ,
                N.CONTENT ,
                N.URGENT_YN ,
                N.HIT ,
                N.ENROLL_DATE ,
                N.MODIFY_DATE ,
                N.DEL_YN ,
                M.NAME,
                D.DEPT_NAME
            FROM NOTICE N
            JOIN EMPLOYEE E
            ON N.WRITER_NO = E.EMP_NO
            JOIN MEMBER M
            ON E.EMP_NO = M.ID
            JOIN DEPT D
            ON E.DEPT_CODE = D.DEPT_CODE
            LEFT JOIN NOTICE_ATTACHMENT A
            ON N.NO = A.REF_NOTI_NO
            WHERE N.NO = #{bno}
            AND N.DEL_YN = 'N'
            """)
    NoticeVo getNoticeDetail(String bno);

    int insertNoticeAttachment(List<String> changeNameList);

    @Update("""
            UPDATE NOTICE
                    SET
                        HIT = HIT + 1
                    WHERE NO = #{bno}
                    AND DEL_YN = 'N'
            """)
    int increseHit(String bno);
}

package com.semi.gam.notice.mapper;

import com.semi.gam.board.vo.AttachmentVo;
import com.semi.gam.board.vo.BoardVo;
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

    int getNoticeCnt(String searchType , String searchValue);

    List<NoticeVo> getNoticeList(PageVo pvo , String searchType, String searchValue);

    @Select("""
            SELECT DISTINCT
                            N.NO ,
                            N.WRITER_NO ,
                            N.TITLE ,
                            N.CONTENT ,
                            N.URGENT_YN ,
                            N.HIT ,
                            N.ENROLL_DATE ,
                            N.MODIFY_DATE ,
                            N.DEL_YN ,
                            M.NAME,
                            D.DEPT_NAME,
                            NA.ORIGIN_NAME,
                            NA.CHANGE_NAME
                        FROM NOTICE N
                        JOIN EMPLOYEE E ON N.WRITER_NO = E.EMP_NO
                        JOIN NOTICE_ATTACHMENT NA ON NA.REF_NOTI_NO = N.NO
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

    @Insert("""
            INSERT INTO NOTICE_ATTACHMENT (NO,REF_NOTI_NO,CHANGE_NAME,ORIGIN_NAME)
            VALUES ((SELECT GET_NOTICE_ATTACHMENT_NO FROM DUAL), SEQ_NOTICE.CURRVAL,#{changeName},#{originName})
            """)
    int insertNoticeAttachment(String changeName, String originName);

    @Update("""
            UPDATE NOTICE
                    SET
                        HIT = HIT + 1
                    WHERE NO = #{bno}
                    AND DEL_YN = 'N'
            """)
    int increseHit(String bno);

    @Update("""
            UPDATE NOTICE
            SET
                TITLE = #{title},
                CONTENT = #{content},
                URGENT_YN = #{urgentYn},
                MODIFY_DATE = SYSDATE
            WHERE NO = #{no} 
            AND DEL_YN = 'N'
            """)
    int edit(NoticeVo vo);

    @Select("""
            SELECT 
            N.NO
            ,N.TITLE
            ,N.CONTENT,
            N.HIT ,
            N.ENROLL_DATE ,
            N.MODIFY_DATE ,
            N.DEL_YN ,
            M.NAME
            FROM NOTICE N
            JOIN EMPLOYEE E
            ON N.WRITER_NO = E.EMP_NO
            JOIN MEMBER M
            ON E.EMP_NO = M.ID
            WHERE N.NO = #{no}
            AND N.DEL_YN = 'N'
            """)
    NoticeVo getNoticeByNo(String no);

    @Update("""
            UPDATE NOTICE
            SET DEL_YN = 'Y',
            MODIFY_DATE = SYSDATE
            WHERE NO = #{bno} AND DEL_YN = 'N'
            """)
    int del(String bno);

    @Select("""
            SELECT *
            FROM NOTICE_ATTACHMENT
            WHERE REF_NOTI_NO = #{bno}
            ORDER BY NO DESC
            """)
    List<AttachmentVo> getAttachmentVoList(String bno);

    @Update("""
            UPDATE NOTICE_ATTACHMENT
            SET CHANGE_NAME = #{changeName},
            ORIGIN_NAME = #{originName}
            WHERE REF_NOTI_NO = #{vo.no}
            """)
    int editAttachment(NoticeVo vo, String originName, String changeName);
}

package com.semi.gam.board.mapper;

import com.semi.gam.board.vo.AttachmentVo;
import com.semi.gam.board.vo.BoardVo;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.page.PageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    List<BoardVo> getBoardList(PageVo pvo , String searchType , String searchValue);

    @Insert("""
            INSERT INTO BOARD
                (
                NO
                , WRITER_NO
                , TITLE
                , CONTENT
                , ENROLL_DATE
                , HIT
                , DEL_YN)
            VALUES
                (
                SEQ_BOARD.NEXTVAL
                , #{writerNo}
                , #{title}
                , #{content}
                , SYSDATE
                , 0
                , 'N'
            )
            """)
    int write(BoardVo vo);


    @Select("""
            SELECT DISTINCT
                 B.NO
                ,B.WRITER_NO
                ,B.TITLE
                ,B.CONTENT
                ,B.ENROLL_DATE
                ,B.HIT
                ,B.DEL_YN
                ,M.NICK
                ,C.NO AS COM_NO
                ,C.COM_WRI_NO 
                ,C.CONTENT AS COM_CONTENT
                ,C.ENROLL_DATE AS COM_ENROLL_DATE
                ,C.MODIFY_DATE AS COM_MODIFY_DATE
                ,CM.NICK AS COM_WRI_NICK
            FROM BOARD B
            JOIN EMPLOYEE E
            ON B.WRITER_NO = E.EMP_NO
            JOIN MEMBER M 
            ON E.EMP_NO = M.ID
            LEFT JOIN BOARD_ATTACHMENT A
            ON B.NO = A.REF_BOARD_NO
            LEFT JOIN BOARD_COMMENT C
            ON B.NO = C.BOARD_NO
            LEFT JOIN MEMBER CM
            ON C.COM_WRI_NO = CM.ID
            WHERE B.NO = #{bno} 
            AND B.DEL_YN = 'N'    
            """)
    BoardVo getBoardDetail(String bno);

    int getBoardCnt(String searchType , String searchValue);

    @Select("""
            SELECT *
            FROM (
                SELECT B.NO
                    ,B.WRITER_NO
                    ,B.TITLE
                    ,B.CONTENT
                    ,B.ENROLL_DATE
                    ,B.HIT
                    ,B.DEL_YN
                    ,M.NICK
                FROM BOARD B
                JOIN EMPLOYEE E
                ON B.WRITER_NO = E.EMP_NO
                JOIN MEMBER M
                ON E.EMP_NO = M.ID
                WHERE B.DEL_YN = 'N'
                ORDER BY B.ENROLL_DATE DESC
            )
            WHERE ROWNUM <= 8
            """)
    List<BoardVo> getBoardHomeList();

    @Select("""
            SELECT *
            FROM(
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
                FROM NOTICE N
                JOIN EMPLOYEE E ON N.WRITER_NO = E.EMP_NO
                JOIN DEPT D ON E.DEPT_CODE = D.DEPT_CODE
                WHERE N.DEL_YN = 'N'
                ORDER BY ENROLL_DATE DESC
             )
             WHERE ROWNUM <= 8
            """)
    List<NoticeVo> getNoticeHomeList();

    int insertBoardAttachment(List<String> changeNameList);

    @Update("""
               UPDATE BOARD
                            SET
                                HIT = HIT + 1
                        WHERE NO = #{bno}
                        AND DEL_YN = 'N'
            """)
    int increseHit(String bno);

    @Update("""
            UPDATE BOARD
            SET TITLE = #{title}
                ,CONTENT =#{content}
            WHERE NO = #{bno}
            """)
    int getBoardEdit(BoardVo vo);

    @Update("""
            UPDATE BOARD
            SET DEL_YN = 'Y'
                , MODIFY_DATE = SYSDATE
            WHERE NO = #{bno}
            """)
    int del(String bno);

    @Select("""
            SELECT *
            FROM BOARD_ATTACHMENT
            WHERE REF_BOARD_NO = #{bno}
            ORDER BY NO DESC
            """)
    List<AttachmentVo> getAttachmentVoList(String bno);

    int updateBoardAttachment(List<String> changeNameList, String no);
}
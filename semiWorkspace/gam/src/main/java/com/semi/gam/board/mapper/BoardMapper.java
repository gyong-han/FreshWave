package com.semi.gam.board.mapper;

import com.semi.gam.board.vo.AttachmentVo;
import com.semi.gam.board.vo.BoardVo;
import com.semi.gam.board.vo.CommentVo;
import com.semi.gam.notice.vo.NoticeVo;
import com.semi.gam.util.page.PageVo;
import org.apache.ibatis.annotations.*;

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
                ,A.ORIGIN_NAME
                ,A.CHANGE_NAME
            FROM BOARD B
            JOIN EMPLOYEE E
            ON B.WRITER_NO = E.EMP_NO
            JOIN MEMBER M 
            ON E.EMP_NO = M.ID
            LEFT JOIN BOARD_ATTACHMENT A
            ON B.NO = A.REF_BOARD_NO
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

//    int insertBoardAttachment(List<String> changeNameList);

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
                ,MODIFY_DATE = SYSDATE
            WHERE NO = #{no}
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

    @Insert("""
            INSERT INTO BOARD_COMMENT
                (NO
                ,BOARD_NO
                ,COM_WRI_NO
                ,CONTENT
                ,ENROLL_DATE
            )
            VALUES
                (SEQ_BOARD_COMMENT.NEXTVAL
                ,#{boardNo}
                ,#{comWriNo}
                ,#{content}
                ,SYSDATE
            )
            """)
    int commentWrite(CommentVo vo);

    @Select("""
            SELECT DISTINCT
                C.NO
                ,C.BOARD_NO
                ,C.COM_WRI_NO
                ,C.CONTENT
                ,C.ENROLL_DATE
                ,C.MODIFY_DATE
                ,M.NICK
            FROM BOARD_COMMENT C
            JOIN MEMBER M ON (C.COM_WRI_NO = M.ID)
            WHERE C.BOARD_NO = #{boardNo}
            """)
    List<CommentVo> getBoardCommentList(String boardNo);

    @Delete("""
            DELETE BOARD_COMMENT
            WHERE NO = #{no}
            """)
    int commentDel(CommentVo vo);

    @Update("""
            UPDATE BOARD_ATTACHMENT
            SET CHANGE_NAME = #{changeName},
            ORIGIN_NAME = #{originName}
            WHERE REF_BOARD_NO = #{vo.no}
            """)
    int editAttachment(BoardVo vo, String originName, String changeName);

    @Insert("""
            INSERT INTO BOARD_ATTACHMENT (NO,REF_BOARD_NO,CHANGE_NAME,ORIGIN_NAME)
            VALUES ((SELECT GET_BOARD_ATTACHMENT_NO FROM DUAL), SEQ_BOARD.CURRVAL,#{changeName},#{originName})
            """)
    int insertBoardAttachment(String changeName, String originName);
}
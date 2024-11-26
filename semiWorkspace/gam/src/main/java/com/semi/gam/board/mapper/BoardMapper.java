package com.semi.gam.board.mapper;

import com.semi.gam.board.vo.BoardVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("""
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
            ON E.EMP_NO = M.EMP_NO
            WHERE DEL_YN = 'N'
            ORDER BY ENROLL_DATE DESC
            """)

    List<BoardVo> getBoardList();

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
                , #{enrollDate}
                , #{hit}
                , #{delYn}
            )
            """)
    int write(BoardVo vo);


    @Select("""
            SELECT
                 B.NO
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
            ON E.EMP_NO = M.EMP_NO
            WHERE B.NO = #{bno} 
            AND B.DEL_YN = 'N'    
            """)
    BoardVo getBoard(String bno);
}

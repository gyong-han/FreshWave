package com.semi.gam.projectMemo.mapper;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.projectMemo.vo.ProjectMemoVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProjectMemoMapper {

    @Insert("""
            INSERT INTO PRJ_MEMO
            (
                NO
                , PRJ_KEY
                , WRITER_NO
                , BP_NO
                , ST_NO
                , PRIORITY
                , NAME
                , CONTENT
                , ING
                , START_DATE
                , END_DATE
            )
            VALUES
            (
                SEQ_PRJ_MEMO.NEXTVAL
                , #{vo.prjKey}
                , #{loginMemberVo.id}
                , #{vo.bpNo}
                , #{vo.stNo}
                , #{vo.priority}
                , #{vo.name}
                , #{vo.content}
                , #{vo.ing}
                , #{vo.startDate}
                , #{vo.endDate}
            )
            """)
    int write(ProjectMemoVo vo, MemberVo loginMemberVo);

    @Select("""
            SELECT
                P.NAME
                , P.NO
                , P.ING
                , B.NAME    AS writerName
                , R.NAME    AS priorityName
                , P.START_DATE
                , P.END_DATE
                , P.PRJ_KEY
            FROM PRJ_MEMO P
            JOIN PRIORITY R ON (R.NO = P.PRIORITY)
            JOIN MEMBER B ON (B.ID = P.WRITER_NO)
            WHERE PRJ_KEY = #{key}
            """)
    List<ProjectMemoVo> list(String key);

    @Select("""
            SELECT
                R.NAME AS priorityName
                , M.ING
                , M.START_DATE
                , M.END_DATE
                , M.BP_NO
                , M.ST_NO
                , M.CONTENT
                , M.NAME
                , N.NAME    AS bpName
                , S.NAME    AS stName
                , M.NO
                , M.PRJ_KEY
            FROM PRJ_MEMO M
            JOIN PRIORITY R ON (R.NO = M.PRIORITY)
            LEFT JOIN BUSINESS N ON (N.NO = M.BP_NO)
            LEFT JOIN STORE S ON (S.NO = M.ST_NO)
            WHERE M.NO = #{no}
            """)
    ProjectMemoVo getMemoVo(String no);

    @Update("""
            UPDATE PRJ_MEMO
                SET
                    PRIORITY = #{priority}
                    , ING = #{ing}
                    , START_DATE = #{startDate}
                    , END_DATE = #{endDate}
                    , CONTENT = #{content}
                    , MODIFY_DATE = SYSDATE
            WHERE NO = #{no}
            """)
    int edit(ProjectMemoVo vo);
}

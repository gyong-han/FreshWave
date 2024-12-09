package com.semi.gam.projectMemo.mapper;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.project.vo.PageVo;
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
            AND P.DEL_YN = 'N'
            OFFSET #{pvo.offset} ROWS FETCH NEXT #{pvo.projectLimit} ROWS ONLY
            """)
    List<ProjectMemoVo> list(String key, PageVo pvo);

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
            AND M.DEL_YN = 'N'
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
            AND DEL_YN = 'N'
            """)
    int edit(ProjectMemoVo vo);

    @Update("""
            UPDATE PRJ_MEMO
                SET
                    DEL_YN = 'Y'
            WHERE NO = #{no}
            AND DEL_YN = 'N'
            """)
    int delete(String no);

    @Select("""
            SELECT 
                    P.NAME
                    , R.NAME AS priorityName
                    , P.START_DATE
                    , P.END_DATE 
                    , P.NO
                    , P.PRJ_KEY
            FROM PRJ_MEMO P
            JOIN PRIORITY R ON (R.NO = P.PRIORITY)
            WHERE PRJ_KEY = #{key}
            AND ING = '진행대기'
            AND DEL_YN ='N'
            """)
    List<ProjectMemoVo> getProjectMemoWaitList(MemberVo loginMemberVo, String key);

    @Select("""
            SELECT 
                    P.NAME
                    , R.NAME AS priorityName
                    , P.START_DATE
                    , P.END_DATE 
                    , P.NO
                    , P.PRJ_KEY
            FROM PRJ_MEMO P
            JOIN PRIORITY R ON (R.NO = P.PRIORITY)
            WHERE PRJ_KEY = #{key}
            AND ING = '진행중'
            AND DEL_YN ='N'
            """)
    List<ProjectMemoVo> getProjectMemoIngList(MemberVo loginMemberVo, String key);
   
    @Select("""
            SELECT 
                    P.NAME
                    , R.NAME AS priorityName
                    , P.START_DATE
                    , P.END_DATE 
                    , P.NO
                    , P.PRJ_KEY
            FROM PRJ_MEMO P
            JOIN PRIORITY R ON (R.NO = P.PRIORITY)
            WHERE PRJ_KEY = #{key}
            AND ING = '진행완료'
            AND DEL_YN ='N'
            """)
    List<ProjectMemoVo> getProjectMemoComplateList(MemberVo loginMemberVo, String key);

    @Select("""
            SELECT COUNT(*)
            FROM PRJ_MEMO
            WHERE PRJ_KEY = #{key}
            AND DEL_YN = 'N'
            """)
    int getProjectMemoListCnt(MemberVo loginMemberVo, String key);

    @Select("""
            SELECT NAME     AS  prjName
            FROM PROJECT
            WHERE KEY = #{key}
            """)
    ProjectMemoVo getProjectName(String key);
}

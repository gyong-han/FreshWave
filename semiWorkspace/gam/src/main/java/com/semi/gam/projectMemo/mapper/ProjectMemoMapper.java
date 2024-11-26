package com.semi.gam.projectMemo.mapper;

import com.semi.gam.projectMemo.vo.ProjectMemoVo;
import org.apache.ibatis.annotations.Insert;

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
                , #{prjKey}
                , #{writerNo}
                , #{bpNo}
                , #{stNo}
                , #{priority}
                , #{name}
                , #{content}
                , #{ing}
                , #{startDate}
                , #{endDate}
            )
            """)
    int write(ProjectMemoVo vo);
}

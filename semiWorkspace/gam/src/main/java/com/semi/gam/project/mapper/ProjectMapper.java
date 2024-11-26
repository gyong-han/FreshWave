package com.semi.gam.project.mapper;

import com.semi.gam.project.vo.ProjectVo;
import org.apache.ibatis.annotations.Insert;

public interface ProjectMapper {

    @Insert("""
            INSERT INTO PROJECT
            (
                KEY
                , WRITER_NO
                , PRIORITY
                , ACCESS_LEVEL
                , NAME
                , START_DATE
                , END_DATE
            )
            VALUES
            (
                #{key}
                , #{writerNo}
                , #{priority}
                , #{accessLevel}
                , #{name}
                , #{startDate}
                , #{endDate}
            )
            
            """)
    int write(ProjectVo vo);


}

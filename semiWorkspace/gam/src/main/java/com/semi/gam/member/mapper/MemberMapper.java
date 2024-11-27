package com.semi.gam.member.mapper;

import com.semi.gam.member.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {


    @Select("""
            SELECT EMP_NO , NAME
            FROM MEMBER
            """)
    List<MemberVo> getEmpVo();
}

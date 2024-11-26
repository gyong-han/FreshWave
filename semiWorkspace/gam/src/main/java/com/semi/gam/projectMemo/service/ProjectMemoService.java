package com.semi.gam.projectMemo.service;

import com.semi.gam.projectMemo.mapper.ProjectMemoMapper;
import com.semi.gam.projectMemo.vo.ProjectMemoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectMemoService {

    private final ProjectMemoMapper mapper;

    public int write(ProjectMemoVo vo) {
        return mapper.write(vo);
    }
}

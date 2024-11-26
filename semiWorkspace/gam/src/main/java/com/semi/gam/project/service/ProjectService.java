package com.semi.gam.project.service;

import com.semi.gam.project.mapper.ProjectMapper;
import com.semi.gam.project.vo.ProjectVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectMapper mapper;

    public int write(ProjectVo vo) {
        return mapper.write(vo);
    }

}

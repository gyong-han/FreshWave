package com.semi.gam.project.vo;

import lombok.Data;

@Data
public class ProjectMemberVo {

    private String profileName;
    private String memberName;
    private String name;
    private String empNo;
    private String deptName;
    private String jobName;
    private String pmAccess;
    private String key;
    private String modiAuth;

    private String[] empNoArr;
    private String[] profileNameArr;
    private String[] memberNameArr;
    private String[] deptNameArr;
    private String[] jobNameArr;
    private String[] modiAuthArr;

}

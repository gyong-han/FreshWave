package com.semi.gam.notice.vo;

import lombok.Data;

@Data
public class NoticeVo {
    private String no;
    private String writerNo;
    private String title;
    private String content;
    private String urgentYn;
    private String hit;
    private String enrollDate;
    private String modifyDate;
    private String delYn;
    private String urgentStatus;

    private String name;

    private String deptName;

    private String originName;
    private String changeName;
}

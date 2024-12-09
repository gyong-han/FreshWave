package com.semi.gam.board.vo;

import lombok.Data;

@Data
public class BoardVo {
    private String no;
    private String title;
    private String content;
    private String writerNo;
    private String hit;
    private String enrollDate;
    private String modifyDate;
    private String delYn;

    private String nick;

    private String comNo;
    private String comWriNick;
    private String comContent;
    private String comEnrollDate;
    private String comModifyDate;

    private String originName;
    private String changeName;

    private String boardNo;
}

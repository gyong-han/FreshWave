package com.semi.gam.schedule.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleVo {
    private String no;
    private String writerNo;
    private String memoNo;
    private String bussinessNo;
    private String busihisNo;
    private String storeNo;
    private String priority;
    private String title;
    private String content;
    private String location;
    private String startDate;
    private String finishDate;
    private String startTime;
    private String finishTime;
    private String almTime;
    private String shareYn;
    private String delYn;

    private LocalDate beginDate;
    private LocalDate endDate;
    private LocalTime beginTime;
    private LocalTime endTime;
}

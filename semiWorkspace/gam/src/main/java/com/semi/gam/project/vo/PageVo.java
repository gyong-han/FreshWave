package com.semi.gam.project.vo;

import lombok.Data;

@Data
public class PageVo {

    private int listCount;
    private int currentPage;
    private int pageLimit;
    private int projectLimit;

    private int maxPage;
    private int startPage;
    private int endPage;

    private int offset;


    public PageVo(int currentPage, int listCount, int pageLimit, int projectLimit) {
        this.currentPage = currentPage;
        this.listCount = listCount;
        this.pageLimit = pageLimit;
        this.projectLimit = projectLimit;

        this.maxPage = (int)Math.ceil((double)listCount/projectLimit);
        this.startPage = (currentPage - 1 ) / pageLimit * pageLimit +1;
        this.endPage = startPage + pageLimit - 1;
        if(endPage > maxPage){
            endPage = maxPage;
        }
        this.offset = projectLimit * (currentPage-1);

    }



}

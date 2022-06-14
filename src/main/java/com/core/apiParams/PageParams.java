package com.core.apiParams;

import lombok.Data;

@Data
public class PageParams {
    private String name;
    private int pageNum;
    private int pageSize;
    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

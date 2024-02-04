package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class PageCriteriaDTO {
    private int pageNum;
    private int amount;
    private int startNum;

    public PageCriteriaDTO(int pageNum,int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Criteria [pageNum=" + pageNum + ", amount=" + amount + "]";
    }
}
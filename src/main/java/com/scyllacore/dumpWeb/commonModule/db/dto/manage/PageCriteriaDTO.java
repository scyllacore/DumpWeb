package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageCriteriaDTO {
    private int pageNum;
    private int amount;

    @Override
    public String toString() {
        return "Criteria [pageNum=" + pageNum + ", amount=" + amount + "]";
    }
}
package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchOptionDTO {
    //공통
    private int userIdIdxFk;
    private String startDate;
    private String endDate;
    private String sortingCriteria;

    //step 4,6,8
    private String fromSite;
    private String toSite;
    private String item;
    private String tel;
    private String state;
    private String searchCarNo;
}

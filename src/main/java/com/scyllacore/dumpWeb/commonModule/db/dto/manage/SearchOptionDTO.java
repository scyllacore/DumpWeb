package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchOptionDTO {
    //공통
    String carNo;
    String startDate;
    String endDate;
    String sortingCriteria;

    //step 4,6,8
    String fromSite;
    String toSite;
    String item;
    String tel;
    String state;
    String searchCarNo;

}

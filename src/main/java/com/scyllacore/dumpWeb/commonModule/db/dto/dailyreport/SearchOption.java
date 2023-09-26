package com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchOption {
    //공통
    String carNo;
    String startDate;
    String endDate;
    String sortingCriteria;

    //step 4,6,8
    String fromSite;
    String toStie;
    String item;
    String searchCarNo;

}

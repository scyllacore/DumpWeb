package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MileageSearchOptionDTO {
    private int writerIdFk;
    private String startDate;
    private String endDate;
    private String item;
    private String sortingCriteria;
    private boolean paymentBtnFlag;

    private int pageNum;
    private int pageAmount;
}

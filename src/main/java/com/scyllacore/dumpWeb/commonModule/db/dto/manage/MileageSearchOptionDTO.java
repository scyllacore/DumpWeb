package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MileageSearchOptionDTO {
    private Long writerIdFk;
    private String startDate;
    private String endDate;
    private String item;
    private String sortingCriteria;
    private Boolean paymentBtnFlag;

    private Integer pageNum;
    private Integer pageAmount;
}

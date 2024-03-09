package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

import java.util.List;

@Data
public class GroupDriveReportSearchOptionDTO {
    private Long groupDriverIdFk;
    private Long groupWriterIdFk;
    private Boolean paymentBtnFlag;
    private String sortingCriteria;

    private String title;
    private String company;
    private List<String> titles;
    private List<String> companies;

}

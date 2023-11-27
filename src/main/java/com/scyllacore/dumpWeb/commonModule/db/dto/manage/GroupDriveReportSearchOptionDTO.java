package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

import java.util.List;

@Data
public class GroupDriveReportSearchOptionDTO {
    private int groupDriverIdFk;
    private int groupWriterIdFk;
    private boolean paymentBtnFlag;
    private String sortingCriteria;

    private List<String> titles;
    private List<String> companies;

}

package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriveReportSearchOptionDTO {
    private int writerIdFk;
    private String startDate;
    private String endDate;
    private String sortingCriteria;
    private boolean paymentBtnFlag;

    private String fromSite;
    private String toSite;
    private String item;
    private String submitterTel;
    private String progress;
}

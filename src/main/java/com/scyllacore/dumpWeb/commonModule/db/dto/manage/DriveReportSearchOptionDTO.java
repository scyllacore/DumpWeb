package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

import java.util.List;

@Data
public class DriveReportSearchOptionDTO {
    private int writerIdFk;
    private String startDate;
    private String endDate;
    private String sortingCriteria;
    private boolean paymentBtnFlag;

    private String companyName;
    private String fromSite;
    private String toSite;
    private String item;
    private String submitterTel;
    private String progress;

    private List<String> companyNames;
    private List<String> fromSites;
    private List<String> toSites;
    private List<String> items;
    private List<String> submitterTels;

}

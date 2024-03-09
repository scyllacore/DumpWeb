package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

import java.util.List;

@Data
public class DriveReportSearchOptionDTO {
    private Long writerIdFk;
    private Long driverIdFk;
    private Long submitterIdFk;
    private String startDate;
    private String endDate;
    private String sortingCriteria;
    private Boolean paymentBtnFlag;

    private String fromSite;
    private String toSite;
    private String item;
    private String progress;

    private String company;
    private String tel;
    private String carNo;

    private List<String> companies;
    private List<String> tels;
    private List<String> carNos;
    private List<String> fromSites;
    private List<String> toSites;
    private List<String> items;

}

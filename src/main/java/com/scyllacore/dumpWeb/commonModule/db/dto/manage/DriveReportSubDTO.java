package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class DriveReportSubDTO {

    private int reportSubId;
    private int reportId_FK;            // LINE :: tSheet FK
    private String fromSite;         // LINE :: 상차지
    private String toSite;           // LINE :: 하차지
    private String item;             // LINE :: 품목
    private double unitPrice;          // LINE :: 단가
    private String memo;              // LINE :: 비고
    private double quantity;             // LINE :: 대수

    private int userId_IDX_FK;          // LINE :: 회원 idx

}

package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class DriveReportDTO {
    private int reportIdx;
    private String driveDate;            // LINE :: 운행일
    private String CarNo;           // LINE :: 차량번호

    private String fromSite;         // LINE :: 상차지
    private String toSite;           // LINE :: 하차지
    private String item;             // LINE :: 품목
    private double unitPrice;          // LINE :: 단가
    private double quantity;             // LINE :: 대수
    private String memo;              // LINE :: 비고
    private String progress;

    private int driverIdIdxFk;
    private int submitterIdIdxFk;
    private int writerIdIdxFk;            // LINE :: 회원 idx

    private boolean driverPaymentChk;
    private boolean submitterPaymentChk;
}

package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class DriveReportDTO {
    private int reportId;
    private String driveDate;            // LINE :: 운행일
    private String carNo;           // LINE :: 차량번호
    private String submitterTel;           // LINE :: 제출처 번호

    private String fromSite;         // LINE :: 상차지
    private String toSite;           // LINE :: 하차지
    private String item;             // LINE :: 품목
    private double unitPrice;          // LINE :: 단가
    private double quantity;             // LINE :: 대수
    private String memo;              // LINE :: 비고
    private String progress;

    private int driverIdFk;
    private int submitterIdFk;
    private int writerIdFk;            // LINE :: 회원 idx

    private boolean driverPaymentChk;
    private boolean submitterPaymentChk;
}

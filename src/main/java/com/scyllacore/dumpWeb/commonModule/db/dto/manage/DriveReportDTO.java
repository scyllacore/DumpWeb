package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class DriveReportDTO {
    private int ReportId;
    private String CarNo;           // LINE :: 차량번호
    private String regDate;            // LINE :: 운행일
    private String srcSubmit;       // LINE :: 제출처
    private String srcSubmitTel;    // LINE :: 제출처 연락처
    private String contactPerson;        // LINE :: 제출처 담당자
    private boolean paymentChk;           // LINE :: 결재여부

    private int userId_IDX_FK;            // LINE :: 회원 idx
}

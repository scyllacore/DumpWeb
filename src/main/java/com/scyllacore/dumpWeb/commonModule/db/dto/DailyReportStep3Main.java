package com.scyllacore.dumpweb.commonModule.db.dto;

import lombok.Data;

@Data
public class DailyReportStep3Main {

    // tSheet 테이블
    private int sheetID;
    private String CarNo;           // LINE :: 차량번호
    private String carSubmit;       // LINE :: 제출처
    private String carSubmitTel;    // LINE :: 제출처 연락처
    private Boolean chk1;           // LINE :: 결재여부
    private String date;            // LINE :: 운행일
    private String salesman;        // LINE :: 제출처 담당자
    
    // VO
    //private long idx;
}

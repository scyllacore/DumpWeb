package com.scyllacore.dumpweb.commonModule.db.dto;

import lombok.Data;

@Data
public class DailyReport {

    // tSheet 테이블
    private long sheetID;
    private String date;          // LINE :: 운행일
    private String startHour;     // LINE :: 시작시간
    private String writer;        // LINE :: 작성자
    private String carSubmit;     // LINE :: 제출처
    private String salesman;      // LINE :: 제출처 담당자
    private String carSubmitTel;  // LINE :: 담당자 휴대폰 번호
    private String fromsite;      // LINE :: 상차지
    private String tosite;        // LINE :: 하차지
    private String item;          // LINE :: 품목
    private String carNo;         // LINE :: 차량번호
    private int qty;              // LINE :: 대수
    private String rem;           // LINE :: 비고
    private Boolean chk2;         // LINE :: 결재여부
    
    // VO
    private long idx;
}

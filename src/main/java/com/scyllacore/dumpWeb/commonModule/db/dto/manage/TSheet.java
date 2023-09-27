package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class TSheet {
    private int sheetID;
    private String CarNo;           // LINE :: 차량번호
    private String date;            // LINE :: 운행일
    private String carSubmit;       // LINE :: 제출처
    private String carSubmitTel;    // LINE :: 제출처 연락처
    private String salesman;        // LINE :: 제출처 담당자
    private boolean chk1;           // LINE :: 결재여부
    private int sheetSS;            // LINE :: 회원 idx
}

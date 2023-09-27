package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class TSheetSub {

    private int sheetsubID;
    private int sheetID2;            // LINE :: tSheet FK
    private String fromsite;         // LINE :: 상차지
    private String tosite;           // LINE :: 하차지
    private String item;             // LINE :: 품목
    private double Qty;              // LINE :: 대수
    private String Rem;              // LINE :: 비고
    private double Qtyup;            // LINE :: 단가
    private int sheetsubSS;          // LINE :: 회원 idx

}

package com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport;

import lombok.Data;

@Data
public class TDrive {

    private int driveID;
    private String carNo;           // LINE :: 차량번호
    private String drvDate;         // LINE :: 날짜
    private String drvClub;         // LINE :: 등록물품
    private int lastKm;             // LINE :: 최종주행거리
    private int useAmt;             // LINE :: 사용금액
    private int useOil;             // LINE :: 주유량
    private String drvRem;          // LINE :: 비고란
    private boolean chk2;           // LINE :: 결제여부
    private int repaddkm;           // LINE :: 교환주행거리
    private String rependdate;      // LINE :: 교환예정일
    private boolean rependchk;      // LINE :: 교환확인여부
    private int driveSS;            // LINE :: 회원 idx

}

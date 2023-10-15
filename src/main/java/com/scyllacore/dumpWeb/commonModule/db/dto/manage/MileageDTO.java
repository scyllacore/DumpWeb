package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class MileageDTO {

    private int mileageId;
    private String carNo;           // LINE :: 차량번호
    private String driveDate;         // LINE :: 날짜
    private String item;         // LINE :: 등록물품
    private int lastKm;             // LINE :: 최종주행거리
    private int usedAmount;             // LINE :: 사용금액
    private int usedOil;             // LINE :: 주유량
    private String memo;          // LINE :: 비고란
    private boolean replActiveChk; // LINE :: 교환 활성화
    private boolean paymentChk;           // LINE :: 결제여부
    private int replKm;           // LINE :: 교환주행거리
    private String replDate;      // LINE :: 교환예정일
    private boolean replChk;      // LINE :: 교환확인여부

    private int writerIdFk;            // LINE :: 회원 idx

}

package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class MileageDTO {

    private int mileageId;
    private String carNo;
    private String driveDate;
    private String item;
    private int lastKm;
    private int usedAmount;
    private int usedOil;
    private String memo;
    private boolean replActiveChk;
    private boolean paymentChk;
    private int replKm;
    private String replDate;
    private boolean replChk;

    private int writerIdFk;

}

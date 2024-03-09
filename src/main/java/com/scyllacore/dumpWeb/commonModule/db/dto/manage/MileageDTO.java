package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class MileageDTO {

    private Long mileageId;
    private String carNo;
    private String driveDate;
    private String item;
    private Integer lastKm;
    private Integer usedAmount;
    private Integer usedOil;
    private String memo;
    private Integer replKm;
    private Boolean paymentChk;
    private Boolean replActiveChk;
    private String replDate;
    private Boolean replChk;

    private Long writerIdFk;

}

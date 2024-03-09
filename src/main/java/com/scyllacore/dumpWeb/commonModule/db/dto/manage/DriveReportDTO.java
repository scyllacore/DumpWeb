package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class DriveReportDTO {
    private Long driveReportId;
    private String driveDate;
    private String receiver;

    private String fromSite;
    private String toSite;
    private String item;
    private Double unitPrice;
    private Double quantity;
    private String memo;
    private String progress;

    private String company;
    private String carNo;

    private Boolean paymentChk;
    private Boolean submitChk;
    private Boolean submitterPaymentChk;
    private Boolean postingChk;

    private Long driverIdFk;
    private Long submitterIdFk;
    private Long writerIdFk;
    private Long groupReportIdFk;

    private Byte userType;
}

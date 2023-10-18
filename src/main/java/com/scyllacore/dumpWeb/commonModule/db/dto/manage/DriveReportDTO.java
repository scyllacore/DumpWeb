package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class DriveReportDTO {
    private int driveReportId;
    private String driveDate;
    private String userInfo;

    private String fromSite;
    private String toSite;
    private String item;
    private double unitPrice;
    private double quantity;
    private String memo;
    private String progress;

    private String carNo;

    private int driverIdFk;
    private int submitterIdFk;
    private int writerIdFk;

    private boolean paymentChk;
    private boolean submitChk;
    private boolean submitterPaymentChk;
    private boolean postingChk;
}

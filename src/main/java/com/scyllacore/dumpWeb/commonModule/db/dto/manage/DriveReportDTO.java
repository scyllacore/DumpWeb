package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class DriveReportDTO {
    private int driveReportId;
    private String driveDate;
    private String receiver;

    private String fromSite;
    private String toSite;
    private String item;
    private double unitPrice;
    private double quantity;
    private String memo;
    private String progress;

    private String company;
    private String carNo;

    private boolean paymentChk;
    private boolean submitChk;
    private boolean submitterPaymentChk;
    private boolean postingChk;

    private int driverIdFk;
    private int submitterIdFk;
    private int writerIdFk;

    private boolean userType;
}

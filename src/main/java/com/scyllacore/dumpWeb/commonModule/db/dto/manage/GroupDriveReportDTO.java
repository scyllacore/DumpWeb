package com.scyllacore.dumpWeb.commonModule.db.dto.manage;


import lombok.Data;

import java.util.List;

@Data
public class GroupDriveReportDTO {
    private int groupId;
    private String title;

    private boolean paymentChk;
    private boolean submitChk;
    private boolean submitterPaymentChk;
    private boolean postingChk;

    private int driverIdFk;
    private int submitterIdFk;
    private int writerIdFk;

    private int userType;

    private List<DriverDTO> driverReports;
}

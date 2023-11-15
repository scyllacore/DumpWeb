package com.scyllacore.dumpWeb.commonModule.db.dto.manage;


import lombok.Data;

import java.util.List;

@Data
public class GroupDriveReportDTO {
    private int groupReportId;
    private String groupDate;
    private String groupReceiver;
    private String groupTitle;
    private String groupMemo;

    private boolean groupPaymentChk;
    private boolean groupSubmitChk;
    private boolean groupSubmitterPaymentChk;
    private boolean groupPostingChk;

    private int groupDriverIdFk;
    private int groupSubmitterIdFk;
    private int groupWriterIdFk;

    private int groupUserType;

    private List<DriveReportDTO> driveReports;
}

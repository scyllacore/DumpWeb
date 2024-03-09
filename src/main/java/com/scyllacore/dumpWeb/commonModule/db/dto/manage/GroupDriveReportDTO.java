package com.scyllacore.dumpWeb.commonModule.db.dto.manage;


import lombok.Data;

import java.util.List;

@Data
public class GroupDriveReportDTO {
    private Long groupReportId;
    private String groupDate;
    private String groupReceiver;
    private String groupTitle;
    private String groupMemo;

    private Boolean groupPaymentChk;
    private Boolean groupSubmitChk;
    private Boolean groupSubmitterPaymentChk;
    private Boolean groupPostingChk;

    private Long groupDriverIdFk;
    private Long groupSubmitterIdFk;
    private Long groupWriterIdFk;
    private Long fileIdFk;

    private Byte groupUserType;

    private List<DriveReportDTO> driveReports;
}

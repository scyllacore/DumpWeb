package com.scyllacore.dumpWeb.commonModule.db.dto.manage;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GroupDriveReportDTO {

    @Data
    public static class Request {
        private Long groupReportId;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate groupDate;
        @NotBlank
        private String groupReceiver;
        @NotBlank
        private String groupTitle;
        @NotBlank
        private String groupCompany;
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

        private List<DriveReportDTO.Request> driveReports;
    }

    @Data
    public static class Response {
        private Long groupReportId;
        private LocalDate groupDate;
        private String groupReceiver;
        private String groupTitle;
        private String groupCompany;
        private String groupCarNo;
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

        private List<DriveReportDTO.Response> driveReports;
    }
}

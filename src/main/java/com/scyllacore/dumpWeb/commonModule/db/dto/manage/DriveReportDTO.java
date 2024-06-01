package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

public class DriveReportDTO {

    @Data
    public static class Request {
        private Long driveReportId;

        @NotNull
        private LocalDate driveDate;

        private String fromSite;
        private String toSite;
        private String item;
        private Double unitPrice;
        private Double quantity;
        private String memo;
        private String progress;

        private Boolean paymentChk;
        private Boolean submitChk;

        private Long driverIdFk;
        private Long submitterIdFk;
        private Long writerIdFk;
        private Long groupReportIdFk;

        private Byte userType;
    }

    @Data
    public static class Response {
        private Long driveReportId;
        private LocalDate driveDate;
        private String receiver;
        private String company;

        private String fromSite;
        private String toSite;
        private String item;
        private Double unitPrice;
        private Double quantity;
        private String memo;
        private String progress;

        private Boolean paymentChk;
        private Boolean submitChk;
        private Boolean submitterPaymentChk;

        private Long submitterIdFk;
        private Long driverIdFk;

        private Byte userType;
    }

}

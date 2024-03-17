package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class DriveReportDTO {

    @Data
    public static class Request {
        private Long driveReportId;
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull
        private LocalDate driveDate;
        @NotBlank
        private String receiver;

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
        private Boolean postingChk;

        private Long driverIdFk;
        private Long submitterIdFk;
        private Long writerIdFk;
        private Long groupReportIdFk;

        private Byte userType;

        private String company;
        private String carNo;
    }

    @Data
    public static class Response {
        private Long driveReportId;
        private LocalDate driveDate;
        private String receiver;

        private String fromSite;
        private String toSite;
        private String item;
        private Double unitPrice;
        private Double quantity;
        private String memo;
        private String progress;

        private Boolean paymentChk;
        private Boolean submitChk;

        private Long submitterIdFk;

        private Byte userType;
    }

}

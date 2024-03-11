package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public class DriveReportSearchOptionDTO {
    @Data
    static class Request {
        private LocalDate startDate;
        private LocalDate endDate;
        @NotBlank
        private String sortingCriteria;
        private Boolean paymentBtnFlag;

        private String fromSite;
        private String toSite;
        private String item;
        private String progress;

        private Long writerIdFk;
        private Long driverIdFk;
        private Long submitterIdFk;

        private String company;
        private String tel;
        private String carNo;
    }

    @Data
    static class Response{
        private List<String> companies;
        private List<String> tels;
        private List<String> carNos;
        private List<String> fromSites;
        private List<String> toSites;
        private List<String> items;
    }

}

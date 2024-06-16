package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

import java.util.List;

public class GroupDriveReportSearchOptionDTO {

    @Data
    public static class Request {
        private String title;
        private String company;
        private String carNo;
        private String sortingCriteria;
        private Boolean paymentBtnFlag;

        private Long groupDriverIdFk;
        private Long groupSubmitterIdFk;
        private Long groupWriterIdFk;
    }

    @Data
    public static class Response {
        private List<String> titles;
        private List<String> companies;
        private List<String> carNos;
    }

}

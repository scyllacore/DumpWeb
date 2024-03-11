package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

import java.time.LocalDate;

public class MileageSearchOptionDTO {

    @Data
    static class Request {
        private Long writerIdFk;
        private LocalDate startDate;
        private LocalDate endDate;
        private String item;
        private String sortingCriteria;
        private Boolean paymentBtnFlag;

        private Integer pageNum;
        private Integer pageAmount;
    }

    @Data
    static class Response {

    }
}

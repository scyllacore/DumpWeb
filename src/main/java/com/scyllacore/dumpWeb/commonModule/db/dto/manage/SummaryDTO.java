package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


public class SummaryDTO {

    @Data
    public static class Request {
        private LocalDate startDate;
        private LocalDate endDate;

        private Long driverIdFk;
        private Long submitterIdFk;
    }

    @Data
    public static class Response {
        Double totalTransportationCost;
        Double totalTrips;
        Double totalCostAmount;
        String lastRegistrationDate;
    }

}

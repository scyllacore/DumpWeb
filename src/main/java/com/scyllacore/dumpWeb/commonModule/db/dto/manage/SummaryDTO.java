package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SummaryDTO {

    Double totalTransportationCost;
    Double totalTrips;
    Double totalCostAmount;
    String lastRegistrationDate;
}

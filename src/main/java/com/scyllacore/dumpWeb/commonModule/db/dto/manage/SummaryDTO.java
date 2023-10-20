package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SummaryDTO {

    double totalTransportationCost;
    double totalTrips;
    double totalCostAmount;
    String lastRegistrationDate;
}

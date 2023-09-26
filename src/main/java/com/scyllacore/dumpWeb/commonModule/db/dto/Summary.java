package com.scyllacore.dumpweb.commonModule.db.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Summary {

    double totalTransportationCost; // 총 운반 금액
    double totalTrips;
    double totalCostAmount;
    Date lastRegistrationDate;
}

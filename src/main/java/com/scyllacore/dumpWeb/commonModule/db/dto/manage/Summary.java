package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Summary {

    double totalTransportationCost; //  LINE :: 총 운반 금액
    double totalTrips; // LINE :: 총 운행 대수
    double totalCostAmount; //  LINE :: 총 비용 금액
    String lastRegistrationDate; // 마지막 등록일
}

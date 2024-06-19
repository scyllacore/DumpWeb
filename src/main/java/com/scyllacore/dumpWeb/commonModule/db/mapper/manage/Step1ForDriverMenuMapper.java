package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SummaryDTO;

import java.util.List;

public interface Step1ForDriverMenuMapper {
    double calculateTotalTransportationCost(SummaryDTO.Request date);

    double calculateTotalTrips(SummaryDTO.Request date);

    List<GroupDriveReportDTO.Response> selectPostingList(SummaryDTO.Request date);
}

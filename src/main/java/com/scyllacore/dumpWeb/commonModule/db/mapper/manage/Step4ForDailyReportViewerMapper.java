package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface Step4ForDailyReportViewerMapper {
    List<String> selectFromSiteSearchOption(Long driverId);
    List<String> selectToSiteSearchOption(Long driverId);
    List<String> selectItemSearchOption(Long driverId);

    List<DriveReportDTO.Response> selectDriveReportListByOption(DriveReportSearchOptionDTO.Request option);

    long updateDriveReportPaymentChk(DriveReportSearchOptionDTO.Request option);

}

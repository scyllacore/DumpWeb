package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step4ForDailyReportViewerMapper {
    List<String> selectCompanySearchOption(int driverId);
    List<String> selectFromSiteSearchOption(int driverId);
    List<String> selectToSiteSearchOption(int driverId);
    List<String> selectItemSearchOption(int driverId);
    List<String> selectSubmitterTelSearchOption(int driverId);

    List<DriveReportSearchOptionDTO> selectDriveReportListByOption(DriveReportSearchOptionDTO option);

    void updateDriveReportPaymentChk(DriveReportSearchOptionDTO option);

}

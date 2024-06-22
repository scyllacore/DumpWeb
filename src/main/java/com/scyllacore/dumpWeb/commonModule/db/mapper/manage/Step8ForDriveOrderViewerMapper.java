package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface Step8ForDriveOrderViewerMapper {

    List<String> selectFromSiteSearchOption(Long submitterId);

    List<String> selectToSiteSearchOption(Long submitterId);

    List<String> selectItemSearchOption(Long submitterId);

    List<DriveReportDTO.Response> selectDriveReportListByOption(DriveReportSearchOptionDTO.Request option);

    long updateDriveReportPaymentChk(DriveReportSearchOptionDTO.Request option);

}

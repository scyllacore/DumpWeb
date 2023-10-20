package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step8ForSubmitterReceiptViewerMapper {
    List<String> selectCarNoSearchOption(int submitterId);

    List<String> selectFromSiteSearchOption(int submitterId);

    List<String> selectToSiteSearchOption(int submitterId);

    List<String> selectItemSearchOption(int submitterId);

    List<DriveReportSearchOptionDTO> selectDriveReportListByOption(DriveReportSearchOptionDTO option);

    void updateDriveReportPaymentChk(DriveReportSearchOptionDTO option);

}

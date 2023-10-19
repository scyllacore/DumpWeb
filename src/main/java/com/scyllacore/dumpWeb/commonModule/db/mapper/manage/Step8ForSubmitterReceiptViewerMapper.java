package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step8ForSubmitterReceiptViewerMapper {
    List<String> selectCarNoSearchOption(int writerId);

    List<String> selectFromSiteSearchOption(int writerId);

    List<String> selectToSiteSearchOption(int writerId);

    List<String> selectItemSearchOption(int writerId);

    List<DriveReportSearchOptionDTO> selectDriveReportListByOption(DriveReportSearchOptionDTO option);

    void updateDriveReportPaymentChk(DriveReportSearchOptionDTO option);

}

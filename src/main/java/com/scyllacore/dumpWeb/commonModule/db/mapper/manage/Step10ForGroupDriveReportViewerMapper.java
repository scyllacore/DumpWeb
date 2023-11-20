package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportSearchOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step10ForGroupDriveReportViewerMapper {
    List<String> selectCompanySearchOption(int driverId);

    List<String> selectSubmitterTelSearchOption(int driverId);

    List<GroupDriveReportSearchOptionDTO> selectDriveReportListByOption(GroupDriveReportSearchOptionDTO option);

    void updateDriveReportPaymentChk(GroupDriveReportSearchOptionDTO option);

}

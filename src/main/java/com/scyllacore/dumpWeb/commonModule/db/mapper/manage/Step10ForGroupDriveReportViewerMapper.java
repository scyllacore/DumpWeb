package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportSearchOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step10ForGroupDriveReportViewerMapper {
    List<String> selectCompanySearchOption(int groupDriverIdFk);

    List<String> selectTitleSearchOption(int groupDriverIdFk);

    List<GroupDriveReportSearchOptionDTO> selectGroupDriveReportListByOption(GroupDriveReportSearchOptionDTO option);

    void updateGroupDriveReportPaymentChk(GroupDriveReportSearchOptionDTO option);

    //void updateDriveReportsPaymentChk()

}

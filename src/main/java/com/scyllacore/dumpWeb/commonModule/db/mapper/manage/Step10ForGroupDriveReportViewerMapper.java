package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportSearchOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step10ForGroupDriveReportViewerMapper {
    List<String> selectCompanySearchOption(Long groupDriverIdFk);

    List<String> selectTitleSearchOption(Long groupDriverIdFk);

    List<GroupDriveReportSearchOptionDTO.Response> selectGroupDriveReportListByOption(
            GroupDriveReportSearchOptionDTO.Request option);

    long updateGroupDriveReportPaymentChk(GroupDriveReportSearchOptionDTO.Request option);
}

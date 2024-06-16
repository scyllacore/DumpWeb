package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportSearchOptionDTO;

import java.util.List;

public interface Step12ForGroupOrderReportViewerMapper {
    List<String> selectCarNoSearchOption(Long groupDriverIdFk);

    List<String> selectTitleSearchOption(Long groupDriverIdFk);

    List<GroupDriveReportDTO.Response> selectGroupOrderReportListByOption(
            GroupDriveReportSearchOptionDTO.Request option);

    long updateGroupOrderReportPaymentChk(GroupDriveReportSearchOptionDTO.Request option);
}

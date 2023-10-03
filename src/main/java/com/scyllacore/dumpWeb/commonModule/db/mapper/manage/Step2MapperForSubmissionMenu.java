package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SummaryDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSubDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step2MapperForSubmissionMenu {

    SummaryDTO selectComputedSummary(SearchOptionDTO option, String userId);

    List<DriveReportSubDTO> selectTodayDispatchStatusList(String userId, String today);
}

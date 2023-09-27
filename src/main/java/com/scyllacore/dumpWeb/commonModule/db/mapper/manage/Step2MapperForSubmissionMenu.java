package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.SearchOption;
import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.Summary;
import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.TSheetSub;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step2MapperForSubmissionMenu {

    Summary selectCalSummary(SearchOption option, String userId);

    List<TSheetSub> selectTodayDispatchStatusList(String userId, String today);
}

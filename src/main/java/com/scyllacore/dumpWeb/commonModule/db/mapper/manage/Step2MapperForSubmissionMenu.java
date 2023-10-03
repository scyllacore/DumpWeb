package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SearchOption;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.Summary;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.TSheetSub;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step2MapperForSubmissionMenu {

    Summary selectComputedSummary(SearchOption option, String userId);

    List<TSheetSub> selectTodayDispatchStatusList(String userId, String today);
}

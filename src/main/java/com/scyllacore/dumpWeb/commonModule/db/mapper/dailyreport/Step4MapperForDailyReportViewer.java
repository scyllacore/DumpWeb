package com.scyllacore.dumpWeb.commonModule.db.mapper.dailyreport;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Step4MapperForDailyReportViewer {

    int insertCarSubmitInfo(DailyReportStep3Main dailyReportStep3Main);
}

package com.scyllacore.dumpweb.commonModule.db.mapper;

import com.scyllacore.dumpweb.commonModule.db.dto.DailyReportStep3Main;

import java.util.List;

public interface DailyReportStep3MainMapper {

    /* FUNCTION ::  등록 */
    int insertDailyReportMain(DailyReportStep3Main dailyReportStep3Main);

    List<DailyReportStep3Main> findJoinDailyReportList(DailyReportStep3Main dailyReportStep3Main);

    /* FUNCTION :: 일보 수정 *//*
    int updateDailyReport(DailyReport dailyReport);

    *//* FUNCTION :: 일보 상세보기 *//*
    DailyReport findDailyReportBySheetID(DailyReport dailyReport);

    /* FUNCTION :: 일보 리스트 조회(전체조건) */
    List<DailyReportStep3Main> findDailyReportList(DailyReportStep3Main dailyReportStep3Main);

    //* FUNCTION :: 일보 리스트 조회 (제출처별) *//
/*    List<DailyReport> findDailyReportListByCarSubmit(DailyReport dailyReport);

    int deleteDailyReport(DailyReport dailyReport);*/
}

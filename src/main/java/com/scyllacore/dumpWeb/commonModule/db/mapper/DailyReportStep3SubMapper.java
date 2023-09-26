package com.scyllacore.dumpweb.commonModule.db.mapper;

import com.scyllacore.dumpweb.commonModule.db.dto.DailyReportStep3Sub;

public interface DailyReportStep3SubMapper {
    
    /* FUNCTION ::  등록 */
    int insertDailyReportSub(DailyReportStep3Sub dailyReportStep3Sub);

    /* FUNCTION :: 일보 수정 *//*
    int updateDailyReport(DailyReport dailyReport);

    *//* FUNCTION :: 일보 상세보기 *//*
    DailyReport findDailyReportBySheetID(DailyReport dailyReport);

    *//* FUNCTION :: 일보 리스트 조회(전체조건) *//*
    List<DailyReport> findDailyReportList(DailyReport dailyReport);

    *//* FUNCTION :: 일보 리스트 조회 (제출처별) *//*
    List<DailyReport> findDailyReportListByCarSubmit(DailyReport dailyReport);

    int deleteDailyReport(DailyReport dailyReport);*/
}

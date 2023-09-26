package com.scyllacore.dumpweb.dailyReportModule.controller;

import com.scyllacore.dumpweb.commonModule.db.dto.DailyReport;
import com.scyllacore.dumpweb.dailyReportModule.service.DailyReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/dailyReport")
@RequiredArgsConstructor
public class DailyReportController {

    private final DailyReportService dailyReportService;

    @RequestMapping(value = "/ajax/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(DailyReport dailyReport) {
        return dailyReportService.save(dailyReport);
    }

    @RequestMapping(value = "/ajax/dataSetting")
    @ResponseBody
    public String dataSetting(DailyReport dailyReport) {
        return dailyReportService.dataSetting(dailyReport);
    }

    @RequestMapping(value = "/ajax/delete")
    @ResponseBody
    public String delete(DailyReport dailyReport) {

        return dailyReportService.delete(dailyReport);
    }
}

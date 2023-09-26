package com.scyllacore.dumpweb.dailyReportModule.controller;

import com.scyllacore.dumpweb.commonModule.db.dto.DailyReport;
import com.scyllacore.dumpweb.dailyReportModule.service.DailyReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dailyReport")
@RequiredArgsConstructor
public class Step6Controller {

    private final DailyReportService dailyReportService;

    @RequestMapping(value = "/carcarelist", method = RequestMethod.GET)
    public String step6(Model model, DailyReport dailyReport) {
        dailyReportService.list(model, dailyReport);
        return "/dailyReport/step6/carcarelist";
    }

}

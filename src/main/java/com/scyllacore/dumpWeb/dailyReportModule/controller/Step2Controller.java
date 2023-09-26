package com.scyllacore.dumpweb.dailyReportModule.controller;

import com.scyllacore.dumpweb.commonModule.db.dto.DailyReport;
import com.scyllacore.dumpweb.commonModule.db.dao.DailyReportStep2Sub;
import com.scyllacore.dumpweb.dailyReportModule.service.DailyReportService;
import com.scyllacore.dumpweb.dailyReportModule.service.Step2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dailyReport")
@RequiredArgsConstructor
public class Step2Controller {

    private final DailyReportService dailyReportService;
    private final Step2Service step2Service;

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String step2(Model model, DailyReport dailyReport) {
        return "/dailyReport/step2/manager";
    }


    @RequestMapping(value = "/step2/getCarAndExpense", method = RequestMethod.GET)
    @ModelAttribute("carAndExpense")
    public List<DailyReportStep2Sub> getCarAndExpense() {
        List<DailyReportStep2Sub> carAndExpense = step2Service.getSummary();
        return carAndExpense;
    }
}


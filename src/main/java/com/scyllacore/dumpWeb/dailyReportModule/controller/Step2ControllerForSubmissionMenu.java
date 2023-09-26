package com.scyllacore.dumpWeb.dailyReportModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.TSheet;
import com.scyllacore.dumpWeb.commonModule.db.dao.DailyReportStep2Sub;
import com.scyllacore.dumpWeb.dailyReportModule.service.DailyReportService;
import com.scyllacore.dumpWeb.dailyReportModule.service.Step2ServiceForSubmissionMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dailyReport")
@RequiredArgsConstructor
public class Step2ControllerForSubmissionMenu {

    private final DailyReportService dailyReportService;
    private final Step2ServiceForSubmissionMenu step2Service;

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String step2(Model model, TSheet dailyReport) {
        return "/dailyReport/step2/manager";
    }


    @RequestMapping(value = "/step2/getCarAndExpense", method = RequestMethod.GET)
    @ModelAttribute("carAndExpense")
    public List<DailyReportStep2Sub> getCarAndExpense() {
        List<DailyReportStep2Sub> carAndExpense = step2Service.getSummary();
        return carAndExpense;
    }
}


package com.scyllacore.dumpWeb.dailyReportModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.SearchOption;
import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.Summary;
import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.TSheetSub;
import com.scyllacore.dumpWeb.dailyReportModule.service.Step2ServiceForSubmissionMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage/step2")
@RequiredArgsConstructor
public class Step2ControllerForSubmissionMenu {

    private final Step2ServiceForSubmissionMenu step2Service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String step2() {
        return "/manage/step2/step2Index";
    }

    @RequestMapping(value = "/ajax/summary", method = RequestMethod.POST)
    @ResponseBody
    public Summary tDriveSummary(SearchOption option) {
        return step2Service.findCalSummary(option);
    }

    @RequestMapping(value = "/ajax/dispatchStatusList", method = RequestMethod.GET)
    @ResponseBody
    public List<TSheetSub> todayDispatchStatusList() {
        return step2Service.findTodayDispatchStatusList();
    }
}


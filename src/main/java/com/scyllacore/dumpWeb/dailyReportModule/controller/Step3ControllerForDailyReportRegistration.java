package com.scyllacore.dumpWeb.dailyReportModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.TSheet;
import com.scyllacore.dumpWeb.commonModule.db.dto.DailyReportStep3Main;
import com.scyllacore.dumpWeb.commonModule.db.dto.DailyReportStep3Sub;
import com.scyllacore.dumpWeb.dailyReportModule.service.DailyReportService;
import com.scyllacore.dumpWeb.dailyReportModule.service.Step3ServiceForDailyReportRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dailyReport")
@RequiredArgsConstructor
public class Step3ControllerForDailyReportRegistration {
    private final DailyReportService dailyReportService;
    private final Step3ServiceForDailyReportRegistration step3Service;

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String step3(Model model, TSheet dailyReport) {
        dailyReportService.list(model, dailyReport);
        return "/dailyReport/step3/form";
    }

    @RequestMapping(value = "/workspace/ajax/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(DailyReportStep3Main dailyReportStep3Main, DailyReportStep3Sub dailyReportStep3Sub) {
        return step3Service.save(dailyReportStep3Main, dailyReportStep3Sub);
    }

}

package com.scyllacore.dumpWeb.dailyReportModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.TSheet;
import com.scyllacore.dumpWeb.dailyReportModule.service.DailyReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dailyReport")
@RequiredArgsConstructor
public class Step5ControllerForVehicleManageMileageRegistration {

    private final DailyReportService dailyReportService;

    @RequestMapping(value = "/carcareform", method = RequestMethod.GET)
    public String step5main(Model model, TSheet dailyReport) {
         dailyReportService.list(model, dailyReport);
        return "/dailyReport/step5/carcareform";
    }

    @RequestMapping(value = "/nextcarcare", method = RequestMethod.GET)
    public String step5next(Model model, TSheet dailyReport) {
        dailyReportService.list(model, dailyReport);
        return "/dailyReport/step5/nextcarcare";
    }

}

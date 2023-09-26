package com.scyllacore.dumpWeb.dailyReportModule.controller;

import com.scyllacore.dumpWeb.dailyReportModule.service.DailyReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dailyReport")
@RequiredArgsConstructor
public class Step8ControllerForSubmissionReceiptViewer {

    private final DailyReportService dailyReportService;

}

package com.scyllacore.dumpweb.dailyReportModule.controller;

import com.scyllacore.dumpweb.dailyReportModule.service.DailyReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dailyReport")
@RequiredArgsConstructor
public class Step8Controller {

    private final DailyReportService dailyReportService;

}

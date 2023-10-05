package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SummaryDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSubDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step2ForSubmissionMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step2")
@RequiredArgsConstructor
public class Step2ForSubmissionMenuController {

    private final Step2ForSubmissionMenuService step2Service;

    @GetMapping(value = "")
    public String step2() {
        return "/manage/step2/step2_index";
    }

    @PostMapping(value = "/ajax/summary")
    @ResponseBody
    public SummaryDTO summary(SearchOptionDTO option) {
        return step2Service.summarize(option);
    }

    @GetMapping(value = "/ajax/todayDispatchStatus")
    @ResponseBody
    public List<DriveReportSubDTO> todayDispatchStatus() {
        return step2Service.findTodayDispatchStatus();
    }
}


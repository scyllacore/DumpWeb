package com.scyllacore.dumpWeb.manageModule.controller;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SummaryDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step1ForDriverMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manage/step1")
@RequiredArgsConstructor
public class Step1ForDriverMenuController {

    private final Step1ForDriverMenuService step1Service;

    @GetMapping(value = "")
    public String step1() {
        return "/manage/step1/step1_index";
    }

    @PostMapping(value = "/fetch/summaryRetrieval")
    public ResponseEntity<SummaryDTO.Response> summaryRetrieval(@RequestBody SummaryDTO.Request date) {
        return step1Service.calculateSummary(date);
    }

    @PostMapping(value = "/fetch/postingRetrieval")
    public ResponseEntity<List<GroupDriveReportDTO.Response>> postingRetrieval(@RequestBody SummaryDTO.Request date) {
        return step1Service.findPostingList(date);
    }

}

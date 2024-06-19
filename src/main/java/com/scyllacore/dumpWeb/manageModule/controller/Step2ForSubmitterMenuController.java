package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SummaryDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step1ForDriverMenuService;
import com.scyllacore.dumpWeb.manageModule.service.Step3ForDriveReportRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manage/step2")
@RequiredArgsConstructor
public class Step2ForSubmitterMenuController {

    @GetMapping(value = "")
    public String step2() {
        return "/manage/step2/step2_index";
    }
}


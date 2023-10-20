package com.scyllacore.dumpWeb.manageModule.controller;


import com.scyllacore.dumpWeb.manageModule.service.Step3ForDriveReportRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage/step9")
@RequiredArgsConstructor
public class Step9ForGroupDriveReportRegistrationController {

    @GetMapping(value = "")
    public String step9() {
        return "/manage/step9/step9_index";
    }
}

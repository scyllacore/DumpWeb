package com.scyllacore.dumpWeb.commonModule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class StepController {
    @GetMapping("/manage/driver")
    public String driverPage(){
        return "redirect:/manage/step1";
    }

    @GetMapping("/manage/submitter")
    public String submitterPage(){
        return "redirect:/manage/step2";
    }
}

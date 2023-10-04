package com.scyllacore.dumpWeb.commonModule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class CommonController {

    @GetMapping(value = "")
    public String startPage() {
        return "redirect:/login";
    }

    @GetMapping("/manage/driver")
    public String driverPage(){
        return "redirect:/manage/step1";
    }

    @GetMapping("/manage/manager")
    public String managerPage(){
        return "redirect:/manage/step2";
    }

}
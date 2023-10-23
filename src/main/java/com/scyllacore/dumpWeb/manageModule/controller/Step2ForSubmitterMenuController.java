package com.scyllacore.dumpWeb.manageModule.controller;

import lombok.RequiredArgsConstructor;
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


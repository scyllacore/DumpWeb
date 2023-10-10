package com.scyllacore.dumpWeb.manageModule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manage/step7")
@RequiredArgsConstructor
public class Step7ForSubmissionOrderRegistrationController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String step7() {
        return "/manage/step7/step7_index";
    }

}

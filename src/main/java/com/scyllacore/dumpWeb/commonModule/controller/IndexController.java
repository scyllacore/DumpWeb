package com.scyllacore.dumpWeb.commonModule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {

    @GetMapping(value = "")
    public String startPage() {
        return "redirect:/login";
    }

}
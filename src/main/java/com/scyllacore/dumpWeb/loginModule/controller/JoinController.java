package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import com.scyllacore.dumpWeb.loginModule.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/join")
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/step1")
    public String joinTypePage() {
        return "login/join_type";
    }

    @GetMapping("/step2")
    public String joinFormPage() {
        return "login/join_form";
    }


    @PostMapping(value = "/ajax/joinFormSubmit")
    @ResponseBody
    public String join(Login loginInfo) {
        return joinService.join(loginInfo);
    }
}

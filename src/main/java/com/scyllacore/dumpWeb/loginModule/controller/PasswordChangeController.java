package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import com.scyllacore.dumpWeb.loginModule.service.PasswordChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passwordChange")
@RequiredArgsConstructor
public class PasswordChangeController {

    private final PasswordChangeService passwordChangeService;

    @GetMapping("/passwordChange")
    public String passwordChangeFormPage() {
        return "/login/password_change_form";
    }

    @PostMapping(value = "/ajax/passwordChange")
    @ResponseBody
    public String passwordChange(Login login) {
        return passwordChangeService.changePassword(login);
    }
}

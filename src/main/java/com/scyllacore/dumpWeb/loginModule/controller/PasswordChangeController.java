package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.loginModule.service.PasswordChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passwordChange")
@RequiredArgsConstructor
public class PasswordChangeController {

    private final PasswordChangeService passwordChangeService;

    @GetMapping("")
    public String passwordChangeFormPage() {
        return "/login/password_change_form";
    }

    @PostMapping(value = "/ajax/changePassword")
    @ResponseBody
    public ResponseDTO<String> passwordChange(LoginDTO loginInfo) {
        return passwordChangeService.changePassword(loginInfo);
    }
}

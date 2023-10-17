package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.loginModule.service.PasswordChangeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class PasswordChangeController {

    private final PasswordChangeService passwordChangeService;

    @GetMapping("/passwordChange")
    public String passwordChangeForm() {
        return "/auth/password_change_form";
    }

    @PostMapping(value = "/auth/fetch/changePassword")
    @ResponseBody
    public ResponseDTO<String> passwordChange(@RequestBody AuthDTO loginInfo, HttpServletRequest request) {
        return passwordChangeService.changePassword(request, loginInfo);
    }
}

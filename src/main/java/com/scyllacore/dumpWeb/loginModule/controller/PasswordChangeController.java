package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.loginModule.service.PasswordChangeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
public class PasswordChangeController {

    private final PasswordChangeService passwordChangeService;

    @GetMapping("/passwordChange")
    public String passwordChangeForm() {
        return "/auth/password_change_form";
    }

    @PostMapping(value = "/auth/fetch/passwordChange")
    @ResponseBody
    public ResponseEntity<ResponseDTO<String>> passwordChange(@Valid @RequestBody AuthDTO.pwdChangeRequest password) {
        return passwordChangeService.changePassword(password);
    }
}

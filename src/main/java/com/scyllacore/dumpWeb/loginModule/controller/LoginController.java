package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.loginModule.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping(value = "/login")
    public String loginFormPage(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        return "/auth/login_form";
    }

    @PostMapping(value = "/auth/fetch/login")
    @ResponseBody
    public ResponseDTO<String> login(@RequestBody AuthDTO loginInfo, HttpServletRequest request) {
        ResponseDTO<String> loginType = loginService.login(loginInfo, request);

        loginService.logout(request);

        if (loginType.getStatus() != 200) {
            return loginType;
        }

        loginType.setData("/manage/" + loginType.getData());
        return loginType;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        loginService.logout(request);

        return "redirect:/login";
    }


}

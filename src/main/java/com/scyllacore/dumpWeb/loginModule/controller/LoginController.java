package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
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

        return "/login/login_form";
    }

    @PostMapping(value = "/ajax/login")
    @ResponseBody
    public ResponseDTO<String> login(LoginDTO loginInfo, HttpServletRequest request) {
        ResponseDTO<String> loginType = loginService.login(loginInfo, request);

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

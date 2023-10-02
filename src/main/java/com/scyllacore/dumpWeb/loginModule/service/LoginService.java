package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import com.scyllacore.dumpWeb.commonModule.db.mapper.login.LoginMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginMapper loginMapper;

    public String tryLogin(Login loginInfo, HttpServletRequest request) {

        Login login = loginMapper.selectUserInfoForValidCheck(loginInfo);

        if (login == null) {
            return "등록되지 않은 ID 입니다.";
        }

        login = loginMapper.selectUserInfo(loginInfo);

        if (login == null) {
            return "패스워드가 일치하지 않습니다.";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginInfo", login);

        return login.getUserPosition();
    }


    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

}

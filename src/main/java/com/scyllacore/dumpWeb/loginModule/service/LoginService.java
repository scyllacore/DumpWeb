package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.login.LoginMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginMapper loginMapper;

    public ResponseDTO<String> login(LoginDTO loginInfo, HttpServletRequest request) {

        LoginDTO login = loginMapper.selectUserInfoForValidCheck(loginInfo);

        if (login == null) {
            return new ResponseDTO<String>(403, "등록되지 않은 ID 입니다.");
        }

        login = loginMapper.selectUserInfo(loginInfo);

        if (login == null) {
            return new ResponseDTO<String>(403, "비밀번호가 일치하지 않습니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginInfo", login);

        return new ResponseDTO<String>(200, login.getUserType());
    }


    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

}

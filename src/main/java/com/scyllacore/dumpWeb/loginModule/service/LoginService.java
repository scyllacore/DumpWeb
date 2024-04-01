package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.LoginMapper;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final LoginMapper loginMapper;

    public ResponseEntity<ResponseDTO<String>> login(AuthDTO.Request loginInfo, HttpServletRequest request) {
        AuthDTO.Request validatedUser = validateUser(loginInfo);
        HttpSession session = request.getSession();
        setSessionUserType(validatedUser, session);
        session.setAttribute("loginInfo", validatedUser);

        return ResponseEntity.ok(new ResponseDTO<>("/manage/" + loginInfo.getUserType()));
    }

    private AuthDTO.Request validateUser(AuthDTO.Request loginInfo) {
        AuthDTO.Request user = loginMapper.selectUserInfoForIdValidCheck(loginInfo);
        if (user == null) {
            throw new IllegalArgumentException("등록되지 않은 ID입니다.");
        }

        user = loginMapper.selectUserInfoForPwdValidCheck(loginInfo);
        if (user == null) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    private void setSessionUserType(AuthDTO.Request login, HttpSession session) {
        if (login.getUserType().equals("driver")) {
            UserDetailDTO.Driver driver = loginMapper.selectDriverInfo(login);
            session.setAttribute("driverInfo", driver);
        } else {
            UserDetailDTO.Submitter submitter = loginMapper.selectSubmitterInfo(login);
            session.setAttribute("submitterInfo", submitter);
        }
    }


    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

}

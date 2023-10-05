package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.login.PasswordChangeMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordChangeService {

    private final PasswordChangeMapper passwordChangeMapper;

    public ResponseDTO<String> changePassword(HttpServletRequest request, LoginDTO loginInfo) {
        LoginDTO sessionLoginInfo = (LoginDTO) request.getSession().getAttribute("loginInfo");
        loginInfo.setUserIdIDX(sessionLoginInfo.getUserIdIDX());

        passwordChangeMapper.updateUserPassword(loginInfo);

        return new ResponseDTO<>(200, "비밀번호가 정상적으로 변경되었습니다.");
    }
}

package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.PasswordChangeMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PasswordChangeService {

    private final PasswordChangeMapper passwordChangeMapper;

    public ResponseDTO<String> changePassword(HttpServletRequest request, AuthDTO loginInfo) {
        AuthDTO sessionLoginInfo = (AuthDTO) request.getSession().getAttribute("loginInfo");
        loginInfo.setUserIdIdx(sessionLoginInfo.getUserIdIdx());

        passwordChangeMapper.updateUserPassword(loginInfo);

        return new ResponseDTO<>(200, "비밀번호가 정상적으로 변경되었습니다.");
    }
}

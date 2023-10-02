package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import com.scyllacore.dumpWeb.commonModule.db.mapper.login.PasswordChangeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordChangeService {

    private final PasswordChangeMapper passwordChangeMapper;

    public String changePassword(Login loginInfo) {
        passwordChangeMapper.updateUserPassword(loginInfo);

        return "비밀번호가 정상적으로 변경되었습니다.";
    }
}

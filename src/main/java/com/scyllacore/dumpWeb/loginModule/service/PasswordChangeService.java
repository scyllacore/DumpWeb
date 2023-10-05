package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.login.PasswordChangeMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordChangeService {

    private final PasswordChangeMapper passwordChangeMapper;

    public ResponseDTO<String> changePassword(LoginDTO loginInfo) {
        System.out.println(loginInfo);

        passwordChangeMapper.updateUserPassword(loginInfo);

        return new ResponseDTO<>(200,"비밀번호가 정상적으로 변경되었습니다.");
    }
}

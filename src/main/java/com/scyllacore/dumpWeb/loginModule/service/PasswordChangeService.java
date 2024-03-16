package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.constant.ResponseType;
import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.PasswordChangeMapper;
import com.scyllacore.dumpWeb.commonModule.exception.RestApiException;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class PasswordChangeService {

    private final PasswordChangeMapper passwordChangeMapper;

    public ResponseEntity<ResponseDTO<String>> changePassword(AuthDTO.Password password, HttpServletRequest request) {
        AuthDTO.Request sessionLoginInfo = (AuthDTO.Request) request.getSession().getAttribute("loginInfo");
        password.setUserIdIdx(sessionLoginInfo.getUserIdIdx());

        if (passwordChangeMapper.updateUserPassword(password) <= 0) {
            throw new RestApiException(ResponseType.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(new ResponseDTO<>("비밀번호가 정상적으로 변경되었습니다."));
    }
}

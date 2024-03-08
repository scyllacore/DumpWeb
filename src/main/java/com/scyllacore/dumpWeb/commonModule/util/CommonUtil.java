package com.scyllacore.dumpWeb.commonModule.util;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonUtil {

    private final HttpServletRequest request;

    public AuthDTO getLoginInfoBySession() {
        return (AuthDTO) request.getSession(true).getAttribute("loginInfo");
    }

    public <T> T getInfoBySession(String name) {
        return (T) request.getSession(true).getAttribute(name);
    }

}

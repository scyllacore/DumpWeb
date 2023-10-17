package com.scyllacore.dumpWeb.commonModule.util;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonUtil {

    private final HttpServletRequest request;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public HttpSession getSession() {
        return request.getSession(true);
    }

    public AuthDTO getLoginInfoBySession() {
        return (AuthDTO) request.getSession(true).getAttribute("loginInfo");
    }


}

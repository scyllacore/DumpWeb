package com.scyllacore.dumpWeb.commonModule.util;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionUtil {

    private final HttpServletRequest request;

    private final String DRIVER_ATTRIBUTE = "driverInfo";
    private final String SUBMITTER_ATTRIBUTE = "submitterInfo";
    private final String LOGIN_ATTRIBUTE = "loginInfo";

    public DriverDTO getDriverInfo() {
        return (DriverDTO) getInfoBySession(DRIVER_ATTRIBUTE);
    }

    public SubmitterDTO getSubmitterInfo() {
        return (SubmitterDTO) getInfoBySession(SUBMITTER_ATTRIBUTE);
    }

    public AuthDTO getLoginInfo() {
        return (AuthDTO) getInfoBySession(LOGIN_ATTRIBUTE);
    }

    public <T> T getInfoBySession(String name) {
        return (T) request.getSession(true).getAttribute(name);
    }
}

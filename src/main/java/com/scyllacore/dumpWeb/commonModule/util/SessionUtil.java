package com.scyllacore.dumpWeb.commonModule.util;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionUtil {

    private final HttpServletRequest request;

    @Getter
    public enum Attribute {
        DRIVER("driverInfo"), SUBMITTER("submitterInfo"), LOGIN("loginInfo");

        private String name;

        Attribute(String name) {
            this.name = name;
        }
    }

    public UserDetailDTO.Driver getDriverInfo() {
        return (UserDetailDTO.Driver) getInfoBySession(Attribute.DRIVER.getName());
    }

    public UserDetailDTO.Submitter getSubmitterInfo() {
        return (UserDetailDTO.Submitter) getInfoBySession(Attribute.SUBMITTER.getName());
    }

    public AuthDTO.Request getLoginInfo() {
        return (AuthDTO.Request) getInfoBySession(Attribute.LOGIN.getName());
    }

    public <T> T getInfoBySession(String name) {
        return (T) getSession(true).getAttribute(name);
    }

    public HttpSession getSession(Boolean flag) {
        return request.getSession(flag);
    }
}

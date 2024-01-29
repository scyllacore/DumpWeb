package com.scyllacore.dumpWeb.commonModule.util;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@RequiredArgsConstructor
public class CommonUtil {

    private final FileUtil fileUtil;

    private final HttpServletRequest request;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public HttpSession getSession() {
        return request.getSession(true);
    }

    public AuthDTO getLoginInfoBySession() {
        return (AuthDTO) request.getSession(true).getAttribute("loginInfo");
    }

    public <T> T getInfoBySession(String name) {
        return (T) request.getSession(true).getAttribute(name);
    }

    @RequestMapping(value = "/image/{fileId}", method = RequestMethod.GET)
    @ResponseBody
    public void getImage(HttpServletResponse response, @PathVariable int fileId) {
        fileUtil.getImageFile(response, fileId);
    }

}

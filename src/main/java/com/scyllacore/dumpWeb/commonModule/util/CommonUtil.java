package com.scyllacore.dumpWeb.commonModule.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommonUtil {

    private final HttpServletRequest request;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public HttpSession getSession() {
        return request.getSession(true);
    }

    /**
     * FUNCTION :: Ajax요청에 대한 리턴 Map 객체 선언
     * @return
     */
    public HashMap<String, Object> returnMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("httpCode", 500);
        return map;
    }

    /**
     * FUNCTION :: JSON 문자열로 변환
     * @return
     */
    public String jsonFormatTransfer(Map<String, Object> map) {
        String result = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            result = mapper.writeValueAsString(map);
        } catch (Exception e) {
            log.error("Exception ["+ e.getMessage() + "]");
        }
        return result;
    }

    public Map<String, Object> mapFormatTransfer(String json) {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> map = new HashMap<>();
        try {
           map =  mapper.readValue(json, new TypeReference<HashMap<String, Object>>() {});

        } catch (Exception e) {
            log.error("Exception ["+ e.getMessage() +"]");
        }
        return map;
    }


}

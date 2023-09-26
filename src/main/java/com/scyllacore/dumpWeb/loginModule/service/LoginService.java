package com.scyllacore.dumpweb.loginModule.service;

import com.scyllacore.dumpweb.commonModule.db.dto.Login;
import com.scyllacore.dumpweb.commonModule.db.mapper.LoginMapper;
import com.scyllacore.dumpweb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginMapper loginMapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CommonUtil commonUtil;

    /**
     * FUNCTION :: 로그인 폼 페이지
     * @return
     */
    public String loginForm() {
        return "/login/loginForm";
    }

    /**
     * 로그인 체크
     * @param loginData 로그인 정보
     * @param request
     * @return
     */
    public String loginChk(Login loginData, HttpServletRequest request) {
        Map<String, Object> rtnMap = commonUtil.returnMap();

        try {
            Login idInfo = loginMapper.userIdValidChk(loginData);
            String rtnUrl = "/login";
            if(idInfo != null) {
                Login loginInfo = loginMapper.findUserInfo(loginData);
                if(loginInfo != null ) {
                    HttpSession session = request.getSession();
                    session.setAttribute("loginInfo", loginInfo);

                    rtnMap.put("httpCode", 200);
                    if (loginInfo.getUserPosition().equals("driver")) {
                        rtnUrl = "/dailyReport/driver";
                    } else {
                        rtnUrl = "/dailyReport/manager";
                    }
                    rtnMap.put("rtnUrl", rtnUrl);
                } else {
                    rtnMap.put("httpCode", 403);
                    rtnMap.put("message", "패스워드가 일치하지 않습니다.");

                }
            } else {
                rtnMap.put("httpCode", 403);
                rtnMap.put("message", "등록되지 않은 ID 입니다.");

            }
        } catch (Exception e) {
            log.error("Exception["+ e.getMessage() +"]");
            rtnMap.put("message", "요청을 처리하는 도중 오류가 발생하였습니다. 관리자에게 문의 부탁드립니다.");
        }
        return commonUtil.jsonFormatTransfer(rtnMap);
    }

    public String joinForm(Login joinData, Model model) {
        model.addAttribute("type", joinData.getType());
        return "/login/joinForm";
    }
    public String joinSelect() {
        return "/login/joinSelect";
    }

    public String join(Login loginData) {
        Map<String, Object> rtnMap = commonUtil.returnMap();


        try {
            if (loginData.getUserPosition().equals("manager")) {
                loginData.setUserTel("010" + loginData.getUserId());
            }
            if (loginMapper.userIdDuplicateChk(loginData) > 0) {
                rtnMap.put("httpCode", 409);
                rtnMap.put("message", "이미 사용 중인 ID 입니다.");
            } else  {
                loginMapper.insertUserInfo(loginData);
                rtnMap.put("httpCode", 200);
            }
        } catch (Exception e) {
            log.error("Exception["+ e.getMessage() +"]");
        }


        return commonUtil.jsonFormatTransfer(rtnMap);
    }

    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();

        return "redirect:/login";
    }

    public String pwChangeForm() {
        return "/login/password_changeForm";
    }

    public String pwChange(Login login) {
        Map<String, Object> rtnMap = commonUtil.returnMap();

        try {
            loginMapper.updateUserPassword(login);
            rtnMap.put("httpCode", 200);
        } catch (Exception e) {
            log.error("Exception["+ e.getMessage() +"]");
        }

        return commonUtil.jsonFormatTransfer(rtnMap);
    }

    public String trialPage() {
        return "/login/trialSelect";
    }

    public String trialLogin(HttpServletRequest request, Login login) {
        Map<String, Object> rtnMap = commonUtil.returnMap();
        String rtnUrl = "";
        try {
            if (login.getType().equals("driver") ) {
                login.setUserId("08호7313");
                rtnUrl = "/dailyReport/driver";
            } else {
                login.setUserId("010-3717-7406");
                rtnUrl = "/dailyReport/manager";

            }

            Login loginData = loginMapper.findAdvUserInfo(login);

            if (loginData != null) {
                loginData.setTestUserChk(true);
                HttpSession session = request.getSession();
                session.setAttribute("loginInfo", loginData);
                rtnMap.put("httpCode", 200);
                rtnMap.put("rtnUrl", rtnUrl);
            }
        } catch (Exception e) {
            log.error("Exception[" + e.getMessage() + "]");
        }




        return commonUtil.jsonFormatTransfer(rtnMap);
    }
}

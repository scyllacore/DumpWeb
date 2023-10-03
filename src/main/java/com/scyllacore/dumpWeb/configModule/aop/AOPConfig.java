package com.scyllacore.dumpWeb.configModule.aop;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class AOPConfig {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("execution (* com.scyllacore.dumpWeb..controller.*Controller.*(..))")
    public Object loginChk(ProceedingJoinPoint pjp) throws Throwable {
        String type = pjp.getSignature().getDeclaringTypeName();
        String className = type.substring(type.lastIndexOf(".") + 1);
        String method_name = pjp.getSignature().getName();

        log.info("----- AOP Check -----");
        log.info(method_name);
        log.info("---------------------");

        Object result;

        if (className.equals("LoginController") && !method_name.equals("passwordChange")) {
            return pjp.proceed();
        }

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        LoginDTO loginInfo = (LoginDTO)  request.getSession().getAttribute("loginInfo");

        if (loginInfo == null) {
            result = "redirect:/login";
        } else {
            result = pjp.proceed();
        }

        return result;
    }
}

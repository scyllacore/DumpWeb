package com.scyllacore.dumpWeb.configModule.aop;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AOPConfig {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final SessionUtil sessionUtil;

    @Around("execution (* com.scyllacore.dumpWeb..controller.*Controller.*(..))")
    public Object loginChk(ProceedingJoinPoint pjp) throws Throwable {

        String type = pjp.getSignature().getDeclaringTypeName();
        String className = type.substring(type.lastIndexOf(".") + 1);
        String method_name = pjp.getSignature().getName();

        log.info("----- AOP Check -----");
        log.info(method_name);
        log.info("---------------------");

        Object result;

        if (!className.contains("Step") && !method_name.contains("passwordChange")) {
            return pjp.proceed();
        }

        AuthDTO.Request loginInfo = sessionUtil.getLoginInfo();

        if (loginInfo == null) {
            result = "redirect:/login";
        } else {
            result = pjp.proceed();
        }

        return result;
    }
}

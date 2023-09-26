package com.scyllacore.dumpweb.configModule.aop;

import com.scyllacore.dumpweb.commonModule.db.dto.Login;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
@RequiredArgsConstructor
public class AOPConfig {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("execution (* com.scyllacore.dumpweb..controller.*Controller.*(..))")
    public Object loginChk(ProceedingJoinPoint pjp) throws Throwable {
        String type = pjp.getSignature().getDeclaringTypeName();
        String className = type.substring(type.lastIndexOf(".") + 1);
        String method_name = pjp.getSignature().getName();
        Object result;
        log.info("----- AOP Check -----");
//        log.info(method_name);
        log.info("---------------------");
        if (className.equals("LoginController") && !method_name.equals("pwchangeForm")) {
            return pjp.proceed();
        } else {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            HttpSession session = request.getSession();
            Login loginInfo = (Login) session.getAttribute("loginInfo");

            if (loginInfo == null) {
                result = "redirect:/";
            } else {
                result = pjp.proceed();
            }
        }
                return result;
    }
}

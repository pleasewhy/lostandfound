package team.cfc.lostandfound.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RequestLogAspect {
    public static final Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);

    @Pointcut("execution(public * team.cfc.lostandfound.controller..*.*(..))")
    public void requestLog() {
    }

    ;

    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info(request.getMethod() + " " + request.getRequestURL() + " " +
                request.getAttribute("username") + " " + request.getRemoteAddr());
    }
}

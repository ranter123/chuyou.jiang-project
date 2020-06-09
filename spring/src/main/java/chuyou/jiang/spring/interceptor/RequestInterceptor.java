package chuyou.jiang.spring.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class RequestInterceptor {

    /**
     * 定义切点
     */
    @Pointcut("@annotation(chuyou.jiang.spring.annotation.MerthodLogInfo)")
    public void requied() {};

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("requied()")
    public void commonMethod(JoinPoint joinPoint) {
        HttpServletRequest request = getHttpServletRequest();
        log.info(request.getRequestURI());
    }


    /**
     * 获取当前HTTP请求
     * @return
     */
    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return servletRequestAttributes.getRequest();
    }
}

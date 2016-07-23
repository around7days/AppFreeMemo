package rms.web.base;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * TraceControllerInterceptorクラス
 * @author
 */
@Aspect
@Component
public class TraceControllerInterceptor {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(TraceControllerInterceptor.class);

    /**
     * Controllerクラス内のメソッド開始時にログを出力
     * @param joinPoint
     */
    @Before("within(rms..*Controller*)")
    public void invokeControllerBefore(JoinPoint joinPoint) {
        logger.debug("[AOP before] called {}#{}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
    }

    /**
     * Controllerクラス内のメソッド終了時にログを出力
     * @param joinPoint
     */
    @After("within(rms..*Controller*)")
    public void invokeControllerAfter(JoinPoint joinPoint) {
        logger.debug("[AOP after ] called {}#{}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
    }
    //
    //    /**
    //     * Serviceクラス内のメソッド開始時にログを出力
    //     * @param joinPoint
    //     */
    //    @Before("within(rms..*Service*)")
    //    public void invokeServiceBefore(JoinPoint joinPoint) {
    //        logger.debug("[AOP before] called {}#{}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
    //    }
    //
    //    /**
    //     * Serviceクラス内のメソッド終了時にログを出力
    //     * @param joinPoint
    //     */
    //    @After("within(rms..*Service*)")
    //    public void invokeServiceAfter(JoinPoint joinPoint) {
    //        logger.debug("[AOP after ] called {}#{}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
    //    }

}
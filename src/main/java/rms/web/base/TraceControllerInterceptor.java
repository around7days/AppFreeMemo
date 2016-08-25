package rms.web.base;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

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
     * @return
     * @throws Throwable
     */
    @Around("within(rms..*Controller*  && !rms.web.base..*))")
    public Object invokeControllerAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String classNm = joinPoint.getTarget().getClass().getName();
        String methodNm = joinPoint.getSignature().getName();

        /*
         * 処理実行前のログ出力
         */
        logger.debug("[AOP before] called {}#{}", classNm, methodNm);

        /*
         * 処理の実行
         */
        Object retVal = joinPoint.proceed();

        /*
         * 処理実行後のログ出力
         */
        if (isURL(retVal)) {
            logger.info("Return URL -> {}", retVal);
        }
        logger.debug("[AOP after ] called {}#{}", classNm, methodNm);

        return retVal;
    }

    /**
     * URLかチェック
     * @param retVal
     * @return true:URL false:URL以外
     */
    private boolean isURL(Object retVal) {
        if (retVal != null && retVal instanceof String) {
            String url = retVal.toString();
            // TODO
            if (url.startsWith("html") || url.startsWith("redirect") || url.startsWith("forward")) {
                // URL
                return true;
            }
        }
        // URL以外
        return false;
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
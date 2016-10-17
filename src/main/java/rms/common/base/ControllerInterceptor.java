package rms.common.base;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ControllerInterceptorクラス
 * @author
 */
@Aspect
@Component
public class ControllerInterceptor {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    /** Controller Method */
    // XXX システム独自仕様
    private static final String controllerClassPath = "rms.web..*Controller*";

    /**
     * Controllerクラス内のメソッド開始時にログを出力
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("within(" + controllerClassPath + ")")
    public Object invokeControllerAround(ProceedingJoinPoint joinPoint) throws Throwable {

        // クラス名・メソッド名の取得
        String classNm = joinPoint.getTarget().getClass().getName();
        String methodNm = joinPoint.getSignature().getName();

        /*
         * 処理実行前のログ出力
         */
        logger.info("called start -> {}#{}", classNm, methodNm);

        /*
         * 処理の実行
         */
        Object retVal = null;
        try {
            retVal = joinPoint.proceed();
        } catch (Exception e) {
            logger.error("called fail -> {}#{}", classNm, methodNm);
            throw e;
        }

        return retVal;
    }

}
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

    /**
     * Controllerクラス内のメソッド開始時にログを出力
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    // XXX システム独自仕様
    @Around("within(rms..*Controller*  && !rms.web.base..*))")
    public Object invokeControllerAround(ProceedingJoinPoint joinPoint) throws Throwable {

        // クラス名・メソッド名の取得
        String classNm = joinPoint.getTarget().getClass().getName();
        String methodNm = joinPoint.getSignature().getName();

        /*
         * 処理実行前のログ出力
         */
        logger.debug("called before -> {}#{}", classNm, methodNm);

        /*
         * 処理の実行
         */
        Object retVal = joinPoint.proceed();

        /*
         * 処理実行後のログ出力
         */
        logger.debug("called after  -> {}#{}", classNm, methodNm);

        return retVal;
    }

}
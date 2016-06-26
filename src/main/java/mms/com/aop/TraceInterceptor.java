package mms.com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceInterceptor {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(TraceInterceptor.class);

    @Before("within(mms..*Controller*)")
    public void invokeBefore(JoinPoint joinPoint) {
        logger.debug("[AOP at before] called {}#{}", joinPoint.getTarget().getClass(),
                     joinPoint.getSignature().getName());
    }

    @After("within(mms..*Controller*)")
    public void invokeAfter(JoinPoint joinPoint) {
        logger.debug("[AOP at after] called {}#{}", joinPoint.getTarget().getClass(),
                     joinPoint.getSignature().getName());
    }

    // @Before("execution(* mms..*Controller.*(..))")
    // public void invokeBefore(JoinPoint joinPoint) {
    // logger.debug("[AOP at before] called {}#{}",
    // joinPoint.getTarget().getClass(),
    // joinPoint.getSignature().getName());
    // }

    // @Around("within(public mms.uniq..*.*Controller(..))")
    // public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws
    // Throwable {
    // Object ret = null;
    // try {
    // System.out.printf("[AOP at around] before invoke, parameters = %s, by
    // %s#%s%n",
    // Arrays.toString(proceedingJoinPoint.getArgs()),
    // proceedingJoinPoint.getTarget().getClass(),
    // proceedingJoinPoint.getSignature().getName());
    //
    // ret = proceedingJoinPoint.proceed();
    // return ret;
    // } finally {
    // System.out.printf("[AOP at around] after invoke, result = %s, %s#%s%n",
    // ret,
    // proceedingJoinPoint.getTarget().getClass(),
    // proceedingJoinPoint.getSignature().getName());
    // }
    // }
}
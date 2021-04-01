package cn.yangwanhao.boot.springboot.test.log.advice.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import cn.yangwanhao.boot.springboot.test.log.advice.annotation.PrintExecuteTimes;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/3/17 19:38
 */
@Slf4j
@Aspect
@Component
public class MethodExecuteTimeAdvice {

    @Around("@annotation(printExecuteTimes)")
    public Object printMethodExecuteTimes(ProceedingJoinPoint proceedingJoinPoint, PrintExecuteTimes printExecuteTimes) {
        Object object = null;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            object = proceedingJoinPoint.proceed();
            stopWatch.stop();
            log.info("接口方法[{}]一共执行了[{}]秒时间。", proceedingJoinPoint.getSignature().toShortString(), stopWatch.getTotalTimeSeconds());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return object;
    }

}

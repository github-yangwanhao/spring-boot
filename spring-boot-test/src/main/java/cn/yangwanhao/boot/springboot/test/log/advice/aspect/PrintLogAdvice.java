package cn.yangwanhao.boot.springboot.test.log.advice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.yangwanhao.boot.springboot.test.log.advice.annotation.PrintLog;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/3/3 16:16
 */
@Slf4j
@Aspect
@Component
public class PrintLogAdvice {

    @Before("@annotation(printLog)")
    public void printRequestLog(JoinPoint joinPoint, PrintLog printLog) {
        if (log.isInfoEnabled()) {
            log.info("执行[{}]开始,执行方法[{}],方法入参[{}]",
                printLog.description(), joinPoint.getSignature().toString(), parseParam(joinPoint.getArgs()));
        }
    }

    @AfterReturning(value = "@annotation(printLog)", returning = "result")
    public void printResponseLog(JoinPoint joinPoint, PrintLog printLog, Object result) {
        if (log.isInfoEnabled()) {
            log.info("执行[{}]结束,执行方法[{}]成功,方法返回值[{}]",
                printLog.description(), joinPoint.getSignature().toString(), parseParam(result));
        }
    }

    @AfterThrowing(pointcut = "@annotation(printLog)", throwing = "ex")
    public void printExceptionLog(JoinPoint joinPoint, PrintLog printLog, Exception ex) {
        if (log.isInfoEnabled()) {
            log.info("执行[{}]失败,执行方法[{}]发生异常,异常信息[{}]",
                printLog.description(), joinPoint.getSignature().toString(), ex.toString());
        }
    }

    private String parseParam(Object[] args) {
        if (args == null || args.length == 0) {
            return "";
        }
        return JSONObject.toJSONString(args);
    }

    private String parseParam(Object args) {
        if (args == null) {
            return "";
        }
        return JSONObject.toJSONString(args);
    }

}

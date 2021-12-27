package cn.yangwanhao.boot.springboot.test.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.yangwanhao.model.exception.BasicException;
import cn.yangwanhao.model.vo.ResponseMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/27 15:42
 */
@Slf4j
@RestControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(BasicException.class)
    public Object customGenericExceptionHandler(BasicException e) {
        log.error("",e);
        return ResponseMessage.error(e.getCode(), e.getMessage());
    }
}

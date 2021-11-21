package cn.yangwanhao.boot.exception.retry.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.yangwanhao.boot.exception.retry.demo.service.IExceptionTradeService;
import cn.yangwanhao.support.ResponseMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/1/14 9:52
 */
@RestController
@Slf4j
@RequestMapping("/exception/test")
public class ExceptionController {

    @Autowired
    private IExceptionTradeService exceptionTradeService;

    @PostMapping("/retry")
    ResponseMessage<Object> retryException(@RequestParam("exceptionId") String exceptionId) {
        return exceptionTradeService.exceptionRetry(exceptionId);
    }

}

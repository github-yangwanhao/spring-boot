package cn.yangwanhao.boot.exception.retry.demo.service;

import cn.yangwanhao.base.common.support.ResponseMessage;
import cn.yangwanhao.boot.exception.retry.demo.model.ExceptionTrade;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/1/14 9:25
 */
public interface IExceptionTradeService {

    ResponseMessage<Integer> registerException(ExceptionTrade exception);

    ResponseMessage<Object> exceptionRetry(String exceptionId);

}

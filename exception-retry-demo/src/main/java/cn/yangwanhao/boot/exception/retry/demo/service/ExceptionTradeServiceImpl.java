package cn.yangwanhao.boot.exception.retry.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.yangwanhao.base.exception.BasicException;
import cn.yangwanhao.boot.exception.retry.demo.mapper.ExceptionTradeMapper;
import cn.yangwanhao.boot.exception.retry.demo.model.ExceptionTrade;
import cn.yangwanhao.support.ResponseMessage;
import cn.yangwanhao.util.IdUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/1/14 9:26
 */
@Service("exceptionTradeService")
@Slf4j
public class ExceptionTradeServiceImpl implements IExceptionTradeService {

    @Autowired
    private ExceptionTradeMapper exceptionTradeMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseMessage<Integer> registerException(ExceptionTrade exception) {
        exception.setExceptionId(String.valueOf(IdUtils.getSnowFlakeId()));
        return ResponseMessage.handleResult(exceptionTradeMapper.insert(exception));
    }

    @Override
    public ResponseMessage<Object> exceptionRetry(String exceptionId) {
        ExceptionTrade trade = exceptionTradeMapper.selectByPrimaryKey(exceptionId);
        if (trade == null) {
            throw new BasicException("500", "未查到异常登记流水");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        String url = "http://localhost:8080" + trade.getRetryUrl();
        HttpMethod httpMethod = HttpMethod.resolve(trade.getRetryHttpMethod().toUpperCase());
        HttpEntity httpEntity = new HttpEntity(trade.getRetryParam(), headers);
        ResponseEntity<Object> response = restTemplate.exchange(url, httpMethod, httpEntity,
            Object.class, new Object());
        ResponseMessage<Object> responseMessage = (ResponseMessage<Object>) ResponseMessage.handleResult(response.getBody());
        if (responseMessage.doSuccess()) {
            exceptionTradeMapper.deleteByPrimaryKey(exceptionId);
        }
        return responseMessage;
    }
}

package cn.yangwanhao.boot.exception.retry.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.yangwanhao.boot.exception.retry.demo.dto.OperateDto;
import cn.yangwanhao.boot.exception.retry.demo.model.ExceptionTrade;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/1/14 10:19
 */
@Slf4j
@Service("operateService")
public class OperateServiceImpl implements IOperateService {

    @Autowired
    private IExceptionTradeService exceptionTradeService;

    @Override
    public Integer add(OperateDto dto) {
        try {
            log.info(dto.toString());
            return dto.getNum1() + dto.getNum2();
        } catch (Exception e) {
            registerException(e.getMessage(), JSONObject.toJSONString(dto));
        }
        return null;
    }

    private void registerException(String errorMessage, String param) {
        ExceptionTrade trade = new ExceptionTrade();
        trade.setErrorCode("500");
        trade.setErrorMessage(errorMessage);
        trade.setModuleName("aaa");
        trade.setRetryUrl("/exception/operate/add");
        trade.setRetryHttpMethod(HttpMethod.POST.name());
        trade.setRetryTimes(3);
        trade.setRemark("加法计算出现异常");
        trade.setCreateTime(new Date());
        trade.setUpdateTime(new Date());
        trade.setRetryParam(param);
        exceptionTradeService.registerException(trade);
    }
}

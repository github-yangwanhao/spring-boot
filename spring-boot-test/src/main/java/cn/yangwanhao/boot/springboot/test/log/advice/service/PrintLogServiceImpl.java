package cn.yangwanhao.boot.springboot.test.log.advice.service;

import org.springframework.stereotype.Service;

import cn.yangwanhao.boot.springboot.test.log.advice.annotation.PrintLog;
import cn.yangwanhao.boot.springboot.test.log.advice.annotation.PrintExecuteTimes;
import cn.yangwanhao.boot.springboot.test.log.advice.request.PrintLogRequestDto;
import cn.yangwanhao.boot.springboot.test.log.advice.response.PrintLogResponseVo;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/3/3 17:13
 */
@Service
public class PrintLogServiceImpl implements IPrintLogService {


    @Override
    @PrintLog(description = "test1方法")
    @PrintExecuteTimes
    public PrintLogResponseVo test1(String param1, String param2, String param3) {
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new PrintLogResponseVo(param1, param2, param3);
    }

    @Override
    @PrintLog(description = "test2方法")
    public PrintLogResponseVo test2(PrintLogRequestDto requestDto) {
        PrintLogResponseVo responseVo = new PrintLogResponseVo();
        responseVo.setResponseParam1(requestDto.getRequestParam1());
        responseVo.setResponseParam2(requestDto.getRequestParam2());
        responseVo.setResponseParam3(requestDto.getRequestParam3());
        return responseVo;
    }
}

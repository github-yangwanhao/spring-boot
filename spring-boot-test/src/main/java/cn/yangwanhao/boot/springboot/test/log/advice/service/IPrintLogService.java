package cn.yangwanhao.boot.springboot.test.log.advice.service;

import cn.yangwanhao.boot.springboot.test.log.advice.request.PrintLogRequestDto;
import cn.yangwanhao.boot.springboot.test.log.advice.response.PrintLogResponseVo;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/3/3 17:13
 */
public interface IPrintLogService {

    PrintLogResponseVo test1(String param1, String param2, String param3);

    PrintLogResponseVo test2(PrintLogRequestDto requestDto);

}

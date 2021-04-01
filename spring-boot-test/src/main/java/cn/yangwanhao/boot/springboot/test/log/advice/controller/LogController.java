package cn.yangwanhao.boot.springboot.test.log.advice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.yangwanhao.boot.springboot.test.log.advice.request.PrintLogRequestDto;
import cn.yangwanhao.boot.springboot.test.log.advice.response.PrintLogResponseVo;
import cn.yangwanhao.boot.springboot.test.log.advice.service.IPrintLogService;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/3/3 16:35
 */
@RestController
@RequestMapping("/springboot/test/log")
public class LogController {

    @Autowired
    private IPrintLogService printLogService;

    @PostMapping("/test1")
    public PrintLogResponseVo test1(@RequestParam("param1") String param1,
        @RequestParam("param2") String param2, @RequestParam("param3") String param3) {
        return printLogService.test1(param1, param2, param3);
    }

    @PostMapping("/test2")
    public PrintLogResponseVo test2(@RequestBody PrintLogRequestDto requestDto) {
        return printLogService.test2(requestDto);
    }

}

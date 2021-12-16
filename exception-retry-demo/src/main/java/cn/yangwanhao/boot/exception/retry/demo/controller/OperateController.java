package cn.yangwanhao.boot.exception.retry.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yangwanhao.boot.exception.retry.demo.dto.OperateDto;
import cn.yangwanhao.boot.exception.retry.demo.service.IOperateService;
import cn.yangwanhao.model.vo.ResponseMessage;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/1/14 10:43
 */
@RestController
@RequestMapping("/exception/operate")
public class OperateController {

    @Autowired
    private IOperateService operateService;

    @PostMapping("/add")
    ResponseMessage<Integer> add(OperateDto operateDto) {
        return ResponseMessage.handleResult(operateService.add(operateDto));
    }

    @PostMapping("/add/retry")
    ResponseMessage<Integer> addRetry(@RequestBody OperateDto operateDto) {
        return ResponseMessage.handleResult(operateService.add(operateDto));
    }

}

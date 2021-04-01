package cn.yangwanhao.boot.springboot.test.chain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yangwanhao.boot.springboot.test.chain.service.AbstractChainService;
import cn.yangwanhao.boot.springboot.test.chain.service.IChainService;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/3/11 19:47
 */
@RestController
public class ChainController {

    @Autowired
    private AbstractChainService chainService1;
    @Autowired
    private AbstractChainService chainService2;
    @Autowired
    private AbstractChainService chainService3;
    @Autowired
    private AbstractChainService chainService4;
    @Autowired
    private AbstractChainService chainService5;

    @PostMapping("/checkNum")
    public Boolean checkNum(Integer num) {
        chainService1.setNext(chainService2);
        chainService2.setNext(chainService3);
        chainService3.setNext(chainService4);
        chainService4.setNext(chainService5);
        return chainService1.isNumIllegal(num);
    }

    @PostMapping("/checkNum1")
    public Boolean checkNum1(Integer num) {
        chainService1.setNext(chainService2);
        chainService2.setNext(chainService3);
        chainService3.setNext(chainService4);
        return chainService1.isNumIllegal(num);
    }

}

package cn.yangwanhao.boot.transaction.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yangwanhao.boot.transaction.test.pojo.request.AddPersonRequest;
import cn.yangwanhao.boot.transaction.test.service.TransactionService;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/4/1 17:49
 */
@RestController
@RequestMapping("/test/transaction")
public class SpringTransactionTestController {

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/operate")
    public Boolean operate(AddPersonRequest request) {
        TransactionService bean = applicationContext.getBean(request.getBeanName(), TransactionService.class);
        return bean.operate(request);
    }

}

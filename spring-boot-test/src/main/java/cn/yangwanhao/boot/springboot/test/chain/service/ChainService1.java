package cn.yangwanhao.boot.springboot.test.chain.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/3/11 19:31
 */
@Slf4j
@Service
public class ChainService1 extends AbstractChainService {

    @Override
    public Boolean isNumIllegal(Integer num) {
        log.info("num 是 {},这是第{}层校验", num, 1);
        return handleResult(num > 10, num);
    }
}

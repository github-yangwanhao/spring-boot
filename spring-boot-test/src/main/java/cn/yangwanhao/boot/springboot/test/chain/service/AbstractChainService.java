package cn.yangwanhao.boot.springboot.test.chain.service;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/3/11 19:25
 */
public abstract class AbstractChainService implements IChainService {

    @Getter
    @Setter
    protected AbstractChainService next;

    protected Boolean handleResult(Boolean result, Integer num) {
        if (result && next != null) {
            return next.isNumIllegal(num);
        }
        return result;
    }

}

package cn.yangwanhao.boot.transaction.test.service;

import cn.yangwanhao.boot.transaction.test.pojo.request.AddPersonRequest;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/4/1 17:55
 */
public interface TransactionService {

    /**
     * 向两张表分别插入一个人的信息
     * @param request request请求参数
     * @return 操作是否成功
     */
    Boolean operate(AddPersonRequest request);

}

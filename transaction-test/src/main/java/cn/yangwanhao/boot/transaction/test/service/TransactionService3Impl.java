package cn.yangwanhao.boot.transaction.test.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yangwanhao.boot.transaction.test.dao.TransactionTestInfoMapper;
import cn.yangwanhao.boot.transaction.test.dao.TransactionTestMapper;
import cn.yangwanhao.boot.transaction.test.pojo.request.AddPersonRequest;
import cn.yangwanhao.boot.transaction.test.pojo.table.TransactionTest;
import cn.yangwanhao.boot.transaction.test.pojo.table.TransactionTestInfo;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/4/1 20:55
 */
@Service("service3")
public class TransactionService3Impl implements TransactionService {

    @Autowired
    private TransactionTestMapper mapper;
    @Autowired
    private TransactionTestInfoMapper infoMapper;

    /**
     * 在插入第一张表后手动设置异常,由于设置了事务,会把第一张表的数据回滚掉
     * @param request request请求参数
     * @return 操作是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean operate(AddPersonRequest request) {
        // 生成UUID
        String id = UUID.randomUUID().toString();
        // 插入第一张表数据
        TransactionTest bean = new TransactionTest();
        bean.setId(id);
        bean.setName(request.getName());
        bean.setAge(request.getAge());
        bean.setGender(request.getGender());
        int result = mapper.insert(bean);
        // 此处发生异常
        int a = 1 / 0;
        // 插入第二张表数据
        TransactionTestInfo beanInfo = new TransactionTestInfo();
        beanInfo.setId(id);
        beanInfo.setPhone(request.getPhone());
        beanInfo.setEmail(request.getEmail());
        int result2 = infoMapper.insert(beanInfo);
        return result + result2 == 2;
    }
}

package cn.yangwanhao.boot.transaction.test.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
@Service("service4")
public class TransactionService4Impl implements TransactionService {

    @Autowired
    private TransactionTestMapper mapper;
    @Autowired
    private TransactionTestInfoMapper infoMapper;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 在插入第二张表后设置异常,但是由于插入第一张表的操作是新启事务并直接提交了的,所以方法的事务回滚不会回滚第一张表的数据,只会回滚第二张表的数据
     * @param request request请求参数
     * @return 操作是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean operate(AddPersonRequest request) {
        // 生成UUID
        String id = UUID.randomUUID().toString();
        // 这种调用方式不会插入数据 会被回滚
        // Integer result = insertFirstTable(request, id);
        // 这种调用方式 这个方法的事务会被先提交,使第一张表的数据插入成功
        Integer result = applicationContext.getBean(TransactionService4Impl.class).insertFirstTable(request, id);
        // 插入第二张表数据
        TransactionTestInfo beanInfo = new TransactionTestInfo();
        beanInfo.setId(id);
        beanInfo.setPhone(request.getPhone());
        beanInfo.setEmail(request.getEmail());
        int result2 = infoMapper.insert(beanInfo);
        // 此处发生异常
        int a = 1 / 0;
        return result + result2 == 2;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Integer insertFirstTable(AddPersonRequest request, String uuid) {
        // 插入第一张表数据
        TransactionTest bean = new TransactionTest();
        bean.setId(uuid);
        bean.setName(request.getName());
        bean.setAge(request.getAge());
        bean.setGender(request.getGender());
        int result = mapper.insert(bean);
        return result;
    }
}

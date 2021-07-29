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
@Service("service5")
public class TransactionService5Impl implements TransactionService {

    @Autowired
    private TransactionTestMapper mapper;
    @Autowired
    private TransactionTestInfoMapper infoMapper;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 在插入第二张表后设置异常,且设置异常不回滚,所以数据会被插入成功
     * @param request request请求参数
     * @return 操作是否成功
     */
    @Override
    @Transactional(noRollbackFor = Exception.class)
    public Boolean operate(AddPersonRequest request) {
        // 生成UUID
        String id = UUID.randomUUID().toString();
        Integer result = insertFirstTable(request, id);
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

    private Integer insertFirstTable(AddPersonRequest request, String uuid) {
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

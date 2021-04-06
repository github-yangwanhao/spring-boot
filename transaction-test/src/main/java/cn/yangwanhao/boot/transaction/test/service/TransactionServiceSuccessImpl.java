package cn.yangwanhao.boot.transaction.test.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yangwanhao.boot.transaction.test.dao.TransactionTestInfoMapper;
import cn.yangwanhao.boot.transaction.test.dao.TransactionTestMapper;
import cn.yangwanhao.boot.transaction.test.pojo.request.AddPersonRequest;
import cn.yangwanhao.boot.transaction.test.pojo.table.TransactionTest;
import cn.yangwanhao.boot.transaction.test.pojo.table.TransactionTestInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/4/1 17:55
 */
@Service("success")
@Slf4j
public class TransactionServiceSuccessImpl implements TransactionService {

    @Autowired
    private TransactionTestMapper mapper;
    @Autowired
    private TransactionTestInfoMapper infoMapper;

    @Override
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
        // 插入第二张表数据
        TransactionTestInfo beanInfo = new TransactionTestInfo();
        beanInfo.setId(id);
        beanInfo.setPhone(request.getPhone());
        beanInfo.setEmail(request.getEmail());
        int result2 = infoMapper.insert(beanInfo);
        return result + result2 == 2;
    }
}

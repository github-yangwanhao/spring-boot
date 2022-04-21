package cn.yangwanhao.boot.springboot.test;

import java.util.List;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

import cn.yangwanhao.boot.springboot.test.enums.EnumOrderStatus;
import cn.yangwanhao.boot.springboot.test.enums.EnumUserStatus;
import cn.yangwanhao.boot.springboot.test.enums.SpringStatusMachineFactory;
import cn.yangwanhao.boot.springboot.test.partition.dao.PartitionDao;
import cn.yangwanhao.boot.springboot.test.partition.dao.PartitionDo;
import cn.yangwanhao.util.util.IdUtils;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootTestApplicationTests {

    @Autowired
    private StringEncryptor encryptor;
    @Autowired
    private SpringStatusMachineFactory springStatusMachineFactory;
    @Autowired
    private PartitionDao partitionDao;

    @Test
    public void testPartition() {
        List<PartitionDo> ids = Lists.newArrayList();
        for (int i=0; i<100000; i++) {
            PartitionDo bean = new PartitionDo();
            bean.setId(IdUtils.getSnowFlakeIdString());
            bean.setId1(IdUtils.getUuid());
            ids.add(bean);
        }
        int result = partitionDao.batchInsert(ids);
        log.info("共insert了{}条", result);
    }

    @Test
    public void testEncryptor() {
        /*
         * 深渊巨坑 springboot 2.x junit test 不支持yml配置文件 必须用properties文件
         */
        String originStr = "******";
        String encryptStr = encryptor.encrypt(originStr);
        String decryptStr = encryptor.decrypt(encryptStr);
        log.info("加密后的字符串:{}", encryptStr);
        log.info("解密后的字符串:{}", decryptStr);
    }

    @Test
    public void testStatusMachine() {
        boolean boolean1 = springStatusMachineFactory.getInstance(EnumOrderStatus.class)
            .checkStatus("3", "5");
        boolean boolean2 = springStatusMachineFactory.getInstance(EnumUserStatus.class)
            .checkStatus("3", "6");
        System.out.println(boolean1);
        System.out.println(boolean2);
    }

}

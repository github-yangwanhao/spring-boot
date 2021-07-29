package cn.yangwanhao.multipledatasource;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.yangwanhao.multipledatasource.mapper.base.DictItemMapper;
import cn.yangwanhao.multipledatasource.mapper.base.DictMapper;
import cn.yangwanhao.multipledatasource.mapper.db.DeptMapper;
import cn.yangwanhao.multipledatasource.mapper.db.ExceptionTradeMapper;
import cn.yangwanhao.multipledatasource.model.base.Dict;
import cn.yangwanhao.multipledatasource.model.base.DictExample;
import cn.yangwanhao.multipledatasource.model.base.DictItem;
import cn.yangwanhao.multipledatasource.model.base.DictItemExample;
import cn.yangwanhao.multipledatasource.model.db.Dept;
import cn.yangwanhao.multipledatasource.model.db.DeptExample;
import cn.yangwanhao.multipledatasource.model.db.ExceptionTrade;
import cn.yangwanhao.multipledatasource.model.db.ExceptionTradeExample;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MultipleDatasourceApplicationTests {

    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private DictItemMapper dictItemMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private ExceptionTradeMapper exceptionTradeMapper;

    @Test
    public void testQuery() {
        List<Dict> dicts = dictMapper.selectByExample(new DictExample());
        log.info("字典表数据是:{}", dicts);
        List<DictItem> dictItems = dictItemMapper.selectByExample(new DictItemExample());
        log.info("字典项表数据是:{}", dictItems);
        List<Dept> depts = deptMapper.selectByExample(new DeptExample());
        log.info("部门表数据是:{}", depts);
        List<ExceptionTrade> exceptionTrades = exceptionTradeMapper.selectByExample(new ExceptionTradeExample());
        log.info("异常表的数据是:{}", exceptionTrades);
    }

}

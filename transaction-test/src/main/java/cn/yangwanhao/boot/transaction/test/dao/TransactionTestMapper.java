package cn.yangwanhao.boot.transaction.test.dao;

import cn.yangwanhao.boot.transaction.test.pojo.table.TransactionTest;
import cn.yangwanhao.boot.transaction.test.pojo.table.TransactionTestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("mapper")
public interface TransactionTestMapper {
    int countByExample(TransactionTestExample example);

    int deleteByExample(TransactionTestExample example);

    int deleteByPrimaryKey(String id);

    int insert(TransactionTest record);

    int insertSelective(TransactionTest record);

    List<TransactionTest> selectByExample(TransactionTestExample example);

    TransactionTest selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TransactionTest record, @Param("example") TransactionTestExample example);

    int updateByExample(@Param("record") TransactionTest record, @Param("example") TransactionTestExample example);

    int updateByPrimaryKeySelective(TransactionTest record);

    int updateByPrimaryKey(TransactionTest record);
}
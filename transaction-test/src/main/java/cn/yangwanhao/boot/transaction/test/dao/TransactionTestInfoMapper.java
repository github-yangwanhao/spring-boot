package cn.yangwanhao.boot.transaction.test.dao;

import cn.yangwanhao.boot.transaction.test.pojo.table.TransactionTestInfo;
import cn.yangwanhao.boot.transaction.test.pojo.table.TransactionTestInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("infoMapper")
public interface TransactionTestInfoMapper {
    int countByExample(TransactionTestInfoExample example);

    int deleteByExample(TransactionTestInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(TransactionTestInfo record);

    int insertSelective(TransactionTestInfo record);

    List<TransactionTestInfo> selectByExample(TransactionTestInfoExample example);

    TransactionTestInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TransactionTestInfo record, @Param("example") TransactionTestInfoExample example);

    int updateByExample(@Param("record") TransactionTestInfo record, @Param("example") TransactionTestInfoExample example);

    int updateByPrimaryKeySelective(TransactionTestInfo record);

    int updateByPrimaryKey(TransactionTestInfo record);
}
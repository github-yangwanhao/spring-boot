package cn.yangwanhao.multipledatasource.mapper.db;

import cn.yangwanhao.multipledatasource.model.db.ExceptionTrade;
import cn.yangwanhao.multipledatasource.model.db.ExceptionTradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionTradeMapper {
    int countByExample(ExceptionTradeExample example);

    int deleteByExample(ExceptionTradeExample example);

    int deleteByPrimaryKey(String exceptionId);

    int insert(ExceptionTrade record);

    int insertSelective(ExceptionTrade record);

    List<ExceptionTrade> selectByExampleWithBLOBs(ExceptionTradeExample example);

    List<ExceptionTrade> selectByExample(ExceptionTradeExample example);

    ExceptionTrade selectByPrimaryKey(String exceptionId);

    int updateByExampleSelective(@Param("record") ExceptionTrade record, @Param("example") ExceptionTradeExample example);

    int updateByExampleWithBLOBs(@Param("record") ExceptionTrade record, @Param("example") ExceptionTradeExample example);

    int updateByExample(@Param("record") ExceptionTrade record, @Param("example") ExceptionTradeExample example);

    int updateByPrimaryKeySelective(ExceptionTrade record);

    int updateByPrimaryKeyWithBLOBs(ExceptionTrade record);

    int updateByPrimaryKey(ExceptionTrade record);
}
package cn.yangwanhao.multipledatasource.mapper.base;

import cn.yangwanhao.multipledatasource.model.base.DictItem;
import cn.yangwanhao.multipledatasource.model.base.DictItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DictItemMapper {
    int countByExample(DictItemExample example);

    int deleteByExample(DictItemExample example);

    int insert(DictItem record);

    int insertSelective(DictItem record);

    List<DictItem> selectByExample(DictItemExample example);

    int updateByExampleSelective(@Param("record") DictItem record, @Param("example") DictItemExample example);

    int updateByExample(@Param("record") DictItem record, @Param("example") DictItemExample example);
}
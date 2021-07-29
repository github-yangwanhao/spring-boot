package cn.yangwanhao.multipledatasource.mapper.base;

import cn.yangwanhao.multipledatasource.model.base.Dict;
import cn.yangwanhao.multipledatasource.model.base.DictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DictMapper {
    int countByExample(DictExample example);

    int deleteByExample(DictExample example);

    int insert(Dict record);

    int insertSelective(Dict record);

    List<Dict> selectByExample(DictExample example);

    int updateByExampleSelective(@Param("record") Dict record, @Param("example") DictExample example);

    int updateByExample(@Param("record") Dict record, @Param("example") DictExample example);
}
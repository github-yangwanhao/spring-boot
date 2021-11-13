package cn.yangwanhao.news_mail.dao;

import cn.yangwanhao.news_mail.model.BaiduTopNews;
import cn.yangwanhao.news_mail.model.BaiduTopNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaiduTopNewsMapper {
    int countByExample(BaiduTopNewsExample example);

    int deleteByExample(BaiduTopNewsExample example);

    int deleteByPrimaryKey(String id);

    int insert(BaiduTopNews record);

    int insertSelective(BaiduTopNews record);

    List<BaiduTopNews> selectByExample(BaiduTopNewsExample example);

    BaiduTopNews selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BaiduTopNews record, @Param("example") BaiduTopNewsExample example);

    int updateByExample(@Param("record") BaiduTopNews record, @Param("example") BaiduTopNewsExample example);

    int updateByPrimaryKeySelective(BaiduTopNews record);

    int updateByPrimaryKey(BaiduTopNews record);
}
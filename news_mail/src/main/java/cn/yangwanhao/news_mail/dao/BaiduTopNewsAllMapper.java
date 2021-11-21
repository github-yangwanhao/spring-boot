package cn.yangwanhao.news_mail.dao;

import cn.yangwanhao.news_mail.model.BaiduTopNewsAll;
import cn.yangwanhao.news_mail.model.BaiduTopNewsAllExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaiduTopNewsAllMapper {
    int countByExample(BaiduTopNewsAllExample example);

    int deleteByExample(BaiduTopNewsAllExample example);

    int deleteByPrimaryKey(String id);

    int insert(BaiduTopNewsAll record);

    int insertSelective(BaiduTopNewsAll record);

    List<BaiduTopNewsAll> selectByExample(BaiduTopNewsAllExample example);

    BaiduTopNewsAll selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BaiduTopNewsAll record, @Param("example") BaiduTopNewsAllExample example);

    int updateByExample(@Param("record") BaiduTopNewsAll record, @Param("example") BaiduTopNewsAllExample example);

    int updateByPrimaryKeySelective(BaiduTopNewsAll record);

    int updateByPrimaryKey(BaiduTopNewsAll record);
}
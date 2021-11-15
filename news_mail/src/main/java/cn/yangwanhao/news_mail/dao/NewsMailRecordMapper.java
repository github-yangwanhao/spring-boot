package cn.yangwanhao.news_mail.dao;

import cn.yangwanhao.news_mail.model.NewsMailRecord;
import cn.yangwanhao.news_mail.model.NewsMailRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsMailRecordMapper {
    int countByExample(NewsMailRecordExample example);

    int deleteByExample(NewsMailRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(NewsMailRecord record);

    int insertSelective(NewsMailRecord record);

    List<NewsMailRecord> selectByExample(NewsMailRecordExample example);

    NewsMailRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NewsMailRecord record, @Param("example") NewsMailRecordExample example);

    int updateByExample(@Param("record") NewsMailRecord record, @Param("example") NewsMailRecordExample example);

    int updateByPrimaryKeySelective(NewsMailRecord record);

    int updateByPrimaryKey(NewsMailRecord record);
}
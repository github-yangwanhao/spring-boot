package cn.yangwanhao.news_mail.dao;

import cn.yangwanhao.news_mail.model.UserEmailInfo;
import cn.yangwanhao.news_mail.model.UserEmailInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserEmailInfoMapper {
    int countByExample(UserEmailInfoExample example);

    int deleteByExample(UserEmailInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserEmailInfo record);

    int insertSelective(UserEmailInfo record);

    List<UserEmailInfo> selectByExample(UserEmailInfoExample example);

    UserEmailInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserEmailInfo record, @Param("example") UserEmailInfoExample example);

    int updateByExample(@Param("record") UserEmailInfo record, @Param("example") UserEmailInfoExample example);

    int updateByPrimaryKeySelective(UserEmailInfo record);

    int updateByPrimaryKey(UserEmailInfo record);
}
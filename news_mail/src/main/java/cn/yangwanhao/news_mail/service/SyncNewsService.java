package cn.yangwanhao.news_mail.service;

import cn.yangwanhao.news_mail.enums.EnumNewsChannelType;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/13 14:44
 */
public interface SyncNewsService {
    /**
     * 同步新闻到数据库
     */
    void syncNewsToDatabase(String batchId);

    /**
     * 渠道类型
     * @return 渠道类型
     */
    EnumNewsChannelType getChannelType();
}

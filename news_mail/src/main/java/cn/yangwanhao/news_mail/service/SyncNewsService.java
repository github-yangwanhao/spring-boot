package cn.yangwanhao.news_mail.service;

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
}

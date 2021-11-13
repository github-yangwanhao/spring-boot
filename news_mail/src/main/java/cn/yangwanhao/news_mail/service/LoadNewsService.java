package cn.yangwanhao.news_mail.service;

import java.util.List;

/**
 * 同步新闻服务
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/13 14:11
 */
public interface LoadNewsService<T> {

    /**
     * 从不同的网站同步新闻头条
     */
    List<T> loadNews();

}

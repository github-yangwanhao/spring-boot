package cn.yangwanhao.news_mail.utils;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/14 19:53
 */
public class RedisLockKeyUtil {

    private static final String SYNC_NEWS_BAIDU = "sync:news:baidu";

    public static String getSyncNewsBaiduKey() {
        return SYNC_NEWS_BAIDU;
    }

}

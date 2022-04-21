package cn.yangwanhao.springboot.redis.string;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/4/21 14:00
 */
public interface IStringTypeService {

    /**
     * 从redis获取字符串
     * @param key
     * @return value
     */
    String getStr(String key);

    /**
     * 向redis存入字符串
     * @param key key
     * @param value value
     * @param time 超时时间
     */
    void setStr(String key, String value, Integer time);
}

package cn.yangwanhao.springboot.redis.list;

import java.util.List;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/4/21 15:03
 */
public interface IListTypeService {

    /**
     * 向list头部插入数据,insert之后的顺序和参数中value的顺序是反的
     * @param key list
     * @param value 数据
     * @return 成功insert的数据个数
     */
    Long headPush(String key, String... value);

    /**
     * 向list尾部插入数据,insert之后的顺序和参数中value的顺序是一致的
     * @param key list
     * @param value 数据
     * @return 成功insert的数据个数
     */
    Long tailPush(String key, String... value);

    /**
     * 获取整个List的值
     * @param key key
     * @return values
     */
    List<String> getList(String key);

    void clearList(String key);
}

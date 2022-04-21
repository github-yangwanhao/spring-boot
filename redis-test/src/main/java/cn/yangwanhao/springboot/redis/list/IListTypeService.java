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
     * @return insert后list的长度
     */
    Long headPush(String key, String... value);

    /**
     * 向list尾部插入数据,insert之后的顺序和参数中value的顺序是一致的
     * @param key list
     * @param value 数据
     * @return insert后list的长度
     */
    Long tailPush(String key, String... value);

    /**
     * 获取整个List的值
     * @param key key
     * @return values
     */
    List<String> getList(String key);

    /**
     * 获取指定下标的value
     * @param key key
     * @param index 洗标 从0开始 也可以是负数,以-1表示列表的最后一个元素,-2表示列表的倒数第二个元素,以此类推
     * @return value
     */
    String getValueFromIndex(String key, Integer index);

    /**
     * 从list的头部弹出一个元素,list会删除并返回这个元素
     * @param key key
     * @return value
     */
    String headPop(String key);

    /**
     * 从list的尾部弹出一个元素,list会删除并返回这个元素
     * @param key key
     * @return value
     */
    String tailPop(String key);

    /**
     * 对一个列表进行修剪(trim),让列表只保留指定区间内的元素,不在指定区间之内的元素都将被删除
     * 区间是双闭原则,start和end所在下标的元素都会被保留
     * @param key key
     * @param start 下标0表示列表的第一个元素,以1表示列表的第二个元素;也可以使用负数下标,以-1表示列表的最后一个元素,-2表示列表的倒数第二个元素
     * @param end 下标0表示列表的第一个元素,以1表示列表的第二个元素;也可以使用负数下标,以-1表示列表的最后一个元素,-2表示列表的倒数第二个元素
     */
    void trim(String key, Integer start, Integer end);

    /**
     * 获取list的长度
     * @param key key
     * @return size
     */
    Long getListSize(String key);

    /**
     * 删除整个list
     * @param key key
     */
    void clearList(String key);
}

package cn.yangwanhao.boot.springboot.test.partition.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/2/18 16:31
 */
@Repository
public interface PartitionDao {

    int batchInsert(List<PartitionDo> ids);
}

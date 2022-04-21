package cn.yangwanhao.boot.springboot.test.partition.dao;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/2/18 18:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartitionDo implements Serializable {

    private String id;

    private String id1;
}

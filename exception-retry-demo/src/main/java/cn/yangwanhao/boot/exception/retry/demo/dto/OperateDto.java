package cn.yangwanhao.boot.exception.retry.demo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/1/14 10:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateDto implements Serializable {

    private Integer num1;

    private Integer num2;

}

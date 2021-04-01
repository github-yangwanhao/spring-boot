package cn.yangwanhao.boot.springboot.test.log.advice.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/3/3 17:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrintLogRequestDto implements Serializable {

    private String requestParam1;

    private String requestParam2;

    private String requestParam3;

}

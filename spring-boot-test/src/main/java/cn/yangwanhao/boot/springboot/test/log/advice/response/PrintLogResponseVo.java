package cn.yangwanhao.boot.springboot.test.log.advice.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/3/3 17:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrintLogResponseVo implements Serializable {

    private String responseParam1;

    private String responseParam2;

    private String responseParam3;

}

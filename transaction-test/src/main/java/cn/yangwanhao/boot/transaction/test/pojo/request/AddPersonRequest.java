package cn.yangwanhao.boot.transaction.test.pojo.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/4/1 20:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPersonRequest implements Serializable {

    private String beanName;

    private String name;

    private Integer age;

    private Integer gender;

    private String phone;

    private String email;

}

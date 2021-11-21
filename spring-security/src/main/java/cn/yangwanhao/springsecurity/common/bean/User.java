package cn.yangwanhao.springsecurity.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -7735786170865421424L;
    private Integer id;
    private String name;
    private Integer age;
    private String password;
}

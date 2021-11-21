package cn.yangwanhao.springsecurity.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Captcha implements Serializable {
    private static final long serialVersionUID = 4558842543753524187L;
    /**
     * 验证码
     */
    private String code;
    /**
     * 剩余验证次数
     */
    private Integer chances;
}

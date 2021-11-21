package cn.yangwanhao.springsecurity.common.bean;

import cn.yangwanhao.springsecurity.common.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    public Message (ErrorCodeEnum e) {
        this.code = e.code();
        this.message = e.msg();
    }
    private Integer code;
    private String message;
}

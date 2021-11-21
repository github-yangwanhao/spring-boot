package cn.yangwanhao.springsecurity.common;

public enum  ErrorCodeEnum {
    /**
     * 全局异常以 G100 开头,101为第一个
     */
    G100101(100101, "参数异常"),
    G100102(100102, "无访问权限"),
    /**
     * 用户异常以 U100 开头,1001为第一个
     */
    // 不能为空系列以1001开头
    U1001001(1001001, "用户名不能为空"),
    U1001002(1001002, "手机号不能为空"),
    U1001003(1001003, "密码不能为空"),
    U1001004(1001004, "确认密码不能为空"),
    U1001005(1001005, "新密码不能为空"),
    U1001006(1001006, "登录名不能为空"),
    U1001007(1001007, "验证码不能为空"),
    U1001008(1001008, "邮箱不能为空"),
    // 格式不正确系列以2001开头
    U1002001(1002001, "手机号格式不正确"),
    U1002002(1002002, "邮箱格式不正确"),
    // 不存在系列以3001开头
    U1003001(1003001, "验证码已过期"),
    // 已存在系列以4001开头
    // 添加失败系列以5001开头
    // 修改失败系列以6001开头
    // 删除失败系列以7001开头
    // 其他：9001开头
    U1009001(1009001, "用户名或密码错误"),
    U1009002(1009002, "验证码错误"),
    U1009003(1009003, "两次输入的密码不一致"),
    U1009004(1009004, "新密码和旧密码不能相同"),
    ;
    private int code;
    private final String msg;

    public String msg() {
        return msg;
    }

    public int code() {
        return code;
    }

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCodeEnum getEnum(int code) {
        for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
            if (ele.code() == code) {
                return ele;
            }
        }
        return null;
    }
}

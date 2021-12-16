package cn.yangwanhao.boot.springboot.test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/16 11:39
 */
@Getter
@AllArgsConstructor
public enum EnumOrderStatus{
    INIT("1", "初始化"),
    APPROVING("2", "审批中"),
    DEALING("3", "处理中"),
    SUCCESS("4", "处理成功"),
    FAILED("5", "处理失败"),
    ;

    /** 状态码 */
    private String code;

    /** 状态描述 */
    private String description;

    /**
     * 根据编码查找枚举
     *
     * @param code 编码
     * @return {@link EnumOrderStatus } 实例
     **/
    public static EnumOrderStatus find(String code) {
        for (EnumOrderStatus instance : EnumOrderStatus.values()) {
            if (instance.getCode()
                .equals(code)) {
                return instance;
            }
        }
        return null;
    }
}


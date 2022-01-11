package cn.yangwanhao.bill.enums;


import cn.yangwanhao.model.enums.EnumBasicErrorCode;
import cn.yangwanhao.model.exception.BasicException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付渠道方式
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2022/1/11 14:09
 */
@Getter
@AllArgsConstructor
public enum EnumPayChannel{
    ALI_PAY("1", "支付宝"),
    WE_CHAT("2", "微信"),
    BANK_CARD("3", "银行卡"),
    CREDIT_CARD("4", "信用卡"),
    CASH("5", "现金"),
    JD("6", "京东白条"),
    ;

    /** 状态码 */
    private String code;

    /** 状态描述 */
    private String description;

    /**
     * 根据编码查找枚举
     *
     * @param code 编码
     * @return {@link EnumPayChannel } 实例
     **/
    public static EnumPayChannel find(String code) {
        for (EnumPayChannel instance : EnumPayChannel.values()) {
            if (instance.getCode()
                .equals(code)) {
                return instance;
            }
        }
        throw new BasicException(EnumBasicErrorCode.G500301, code);
    }

    /**
     * 根据描述查找枚举
     *
     * @param desc 描述
     * @return {@link EnumPayChannel } 实例
     **/
    public static EnumPayChannel findByDesc(String desc) {
        for (EnumPayChannel instance : EnumPayChannel.values()) {
            if (instance.getDescription().equals(desc)) {
                return instance;
            }
        }
        throw new BasicException(EnumBasicErrorCode.G500301, desc);
    }
}


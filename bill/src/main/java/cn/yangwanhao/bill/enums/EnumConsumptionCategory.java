package cn.yangwanhao.bill.enums;


import cn.yangwanhao.model.enums.EnumBasicErrorCode;
import cn.yangwanhao.model.exception.BasicException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消费类型枚举
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2022/1/11 14:20
 */
@Getter
@AllArgsConstructor
public enum EnumConsumptionCategory {
    FOOD("1", "餐饮美食"),
    DAILY_USE("2", "生活日用"),
    TRANSPORTATION("3", "交通出行"),
    HEALTH("4", "医疗保健"),
    CLOTHES("5", "服饰美容"),
    TOP_UP("6", "充值缴费"),
    COMMUNICATION("7", "电子通讯"),
    RELAX("8", "休闲娱乐"),
    HOUSING("9", "住房物业"),
    BOOKS("10", "图书教育"),
    HOTEL_AND_TRAVEL("11", "酒店旅行"),
    SOCIAL("12", "人情往来"),
    OTHER("13", "其他"),
    ;

    /** 状态码 */
    private String code;

    /** 状态描述 */
    private String description;

    /**
     * 根据编码查找枚举
     *
     * @param code 编码
     * @return {@link EnumConsumptionCategory } 实例
     **/
    public static EnumConsumptionCategory find(String code) {
        for (EnumConsumptionCategory instance : EnumConsumptionCategory.values()) {
            if (instance.getCode().equals(code)) {
                return instance;
            }
        }
        throw new BasicException(EnumBasicErrorCode.G500301, code);
    }

    /**
     * 根据描述查找枚举
     *
     * @param desc 描述
     * @return {@link EnumConsumptionCategory } 实例
     **/
    public static EnumConsumptionCategory findByDesc(String desc) {
        for (EnumConsumptionCategory instance : EnumConsumptionCategory.values()) {
            if (instance.getDescription().equals(desc)) {
                return instance;
            }
        }
        throw new BasicException(EnumBasicErrorCode.G500301, desc);
    }
}


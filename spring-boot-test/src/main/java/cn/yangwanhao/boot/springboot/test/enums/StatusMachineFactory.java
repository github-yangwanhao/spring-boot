package cn.yangwanhao.boot.springboot.test.enums;

import cn.yangwanhao.util.support.AbstractStatusMachine;

/**
 * 工厂类
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/16 13:58
 */
public class StatusMachineFactory {

    public static AbstractStatusMachine getInstance(Class<?> clazz) {
        switch (clazz.getSimpleName()) {
            case "EnumOrderStatus":
                return new OrderStatusMachine();
            case "EnumUserStatus":
                return new UserStatusMachine();
            default:
                throw new RuntimeException("未找到对应枚举的状态机");
        }
    }

}

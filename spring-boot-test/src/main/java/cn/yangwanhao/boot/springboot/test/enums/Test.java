package cn.yangwanhao.boot.springboot.test.enums;

import cn.yangwanhao.util.support.AbstractStatusMachine;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/16 11:39
 */
public class Test {

    public static void main(String[] args) {
        boolean flag = StatusMachineFactory.getInstance(EnumOrderStatus.class).checkStatus("3", "5");
        boolean flag1 = StatusMachineFactory.getInstance(EnumUserStatus.class).checkStatus("3", "6");
        System.out.println(flag);
        System.out.println(flag1);
    }

}

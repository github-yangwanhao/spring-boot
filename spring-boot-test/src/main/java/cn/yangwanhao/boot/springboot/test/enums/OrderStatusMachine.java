package cn.yangwanhao.boot.springboot.test.enums;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.yangwanhao.util.po.StatusMachineRelation;
import cn.yangwanhao.util.support.AbstractStatusMachine;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/16 11:40
 */
@Component
public class OrderStatusMachine extends AbstractStatusMachine {

    public OrderStatusMachine() {
        init();
    }

    @Override
    protected Class<?> enumClass() {
        return EnumOrderStatus.class;
    }

    @Override
    protected void init() {
        List<StatusMachineRelation> list = new ArrayList<>();
        list.add(new StatusMachineRelation(EnumOrderStatus.INIT.getCode(), EnumOrderStatus.APPROVING.getCode()));
        list.add(new StatusMachineRelation(EnumOrderStatus.APPROVING.getCode(), EnumOrderStatus.DEALING.getCode()));
        list.add(new StatusMachineRelation(EnumOrderStatus.DEALING.getCode(), EnumOrderStatus.SUCCESS.getCode()));
        list.add(new StatusMachineRelation(EnumOrderStatus.DEALING.getCode(), EnumOrderStatus.FAILED.getCode()));
        super.map.put(enumClass(), list);
    }
}

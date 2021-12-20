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
public class UserStatusMachine extends AbstractStatusMachine {

    public UserStatusMachine() {
        init();
    }

    @Override
    public Class<?> enumClass() {
        return EnumUserStatus.class;
    }

    @Override
    protected void init() {
        List<StatusMachineRelation> list = new ArrayList<>();
        list.add(new StatusMachineRelation(EnumUserStatus.INIT.getCode(), EnumUserStatus.APPROVING.getCode()));
        list.add(new StatusMachineRelation(EnumUserStatus.APPROVING.getCode(), EnumUserStatus.DEALING.getCode()));
        list.add(new StatusMachineRelation(EnumUserStatus.DEALING.getCode(), EnumUserStatus.SUCCESS.getCode()));
        list.add(new StatusMachineRelation(EnumUserStatus.DEALING.getCode(), EnumUserStatus.FAILED.getCode()));
        super.map.put(enumClass(), list);
    }
}

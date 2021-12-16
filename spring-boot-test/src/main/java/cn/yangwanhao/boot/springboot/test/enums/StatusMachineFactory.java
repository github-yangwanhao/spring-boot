package cn.yangwanhao.boot.springboot.test.enums;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import cn.yangwanhao.util.support.AbstractStatusMachine;
import cn.yangwanhao.util.util.CollectionUtils;

/**
 * 工厂类
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/16 13:58
 */
public class StatusMachineFactory {

    private static final String PACKAGE_PATH = "cn.yangwanhao";

    private static final Map<Class<?>, AbstractStatusMachine> MAP = new HashMap<>();

    static {
        Reflections reflections = new Reflections(PACKAGE_PATH);
        Set<Class<? extends AbstractStatusMachine>> childrenClasses = reflections.getSubTypesOf(AbstractStatusMachine.class);
        if (CollectionUtils.isNotEmpty(childrenClasses)) {
            for (Class<? extends AbstractStatusMachine> clazz : childrenClasses) {
                AbstractStatusMachine machine = null;
                try {
                    machine = clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                Class<?> invoke = null;
                try {
                    Method enumClass = clazz.getDeclaredMethod("enumClass", null);
                    invoke = (Class<?>)enumClass.invoke(machine);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                MAP.put(invoke, machine);
            }
        }
    }

    public static AbstractStatusMachine getInstance(Class<?> clazz) {
        AbstractStatusMachine abstractStatusMachine = MAP.get(clazz);
        if (abstractStatusMachine == null) {
            throw new RuntimeException("未找到对应枚举的状态机");
        }
        return abstractStatusMachine;
    }

}

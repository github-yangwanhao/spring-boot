package cn.yangwanhao.boot.springboot.test.enums;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yangwanhao.util.support.AbstractStatusMachine;
import cn.yangwanhao.util.util.CollectionUtils;
import lombok.SneakyThrows;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/20 10:51
 */
@Component
public class SpringStatusMachineFactory {

    @Autowired
    private List<AbstractStatusMachine> abstractStatusMachines;

    @SneakyThrows
    @Autowired
    private Map<Class<?>, AbstractStatusMachine> map() {
        Map<Class<?>, AbstractStatusMachine> map = new HashMap<>();
        if (CollectionUtils.isEmpty(abstractStatusMachines)) {
            return map;
        }
        for (AbstractStatusMachine s : abstractStatusMachines) {
            Method enumClass = s.getClass()
                .getDeclaredMethod("enumClass", null);
            Class<?> invoke = (Class<?>)enumClass.invoke(s);
            map.put(invoke, s);
        }
        return map;
    }

    public AbstractStatusMachine getInstance(Class<?> enumClass) {
        return map().get(enumClass);
    }
}

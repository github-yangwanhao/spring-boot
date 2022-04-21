package cn.yangwanhao.boot.springboot.test.config;

import java.util.Objects;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/3/3 18:54
 */
@Component
public class BeanPostProcessorConfig implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, "childrenClass1")) {
            return Proxy.newProxyInstance(BeanPostProcessorConfig.class.getClassLoader(), bean.getClass().getInterfaces(),
                (proxy, method, args) -> method.invoke(proxy, args));
        }
        return bean;
    }
}

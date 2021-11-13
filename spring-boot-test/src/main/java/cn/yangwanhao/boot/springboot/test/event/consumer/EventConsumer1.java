package cn.yangwanhao.boot.springboot.test.event.consumer;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.yangwanhao.boot.springboot.test.event.bean.EventMessageBean;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/7/8 17:47
 */
@Service
@Slf4j
public class EventConsumer1 {

    @Async
    @EventListener
    public void consume(EventMessageBean bean) {
        log.info("消费到异步消息,异步消息内容是:{}", bean);
    }

}

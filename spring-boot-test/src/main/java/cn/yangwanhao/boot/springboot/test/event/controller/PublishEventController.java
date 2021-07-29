package cn.yangwanhao.boot.springboot.test.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yangwanhao.boot.springboot.test.event.bean.EventMessageBean;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/7/8 17:40
 */
@RestController
public class PublishEventController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @RequestMapping("/event/test/publish")
    public void publishEvent(String messageId, String messageBody) {
        EventMessageBean bean = new EventMessageBean();
        bean.setMessageId(messageId);
        bean.setMessageBody(messageBody);
        eventPublisher.publishEvent(bean);
    }

}

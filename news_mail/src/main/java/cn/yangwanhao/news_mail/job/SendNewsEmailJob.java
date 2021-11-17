package cn.yangwanhao.news_mail.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.yangwanhao.news_mail.enums.EnumNewsChannelType;
import cn.yangwanhao.news_mail.service.NewsMailRecordService;
import cn.yangwanhao.news_mail.service.QueryNewsService;
import cn.yangwanhao.news_mail.service.UserEmailService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/15 14:45
 */
@Slf4j
@Component
public class SendNewsEmailJob {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserEmailService userEmailService;
    @Autowired
    private List<QueryNewsService> queryNewsServices;
    @Autowired
    private NewsMailRecordService newsMailRecordService;

    /**
     * 周一到周五 - 9:05,14:05,18:05 执行发送邮件的任务
     * @param
     * @return
     */
    @Scheduled(cron = "${cn.yangwanhao.send_email.cron.sendNewsEmailJob}")
    public void process() {
        Map<String, List<String>> batchIdListMap = new HashMap<>();
        // 查询出所有的邮件
        String emails = userEmailService.getEmailAddressListStr();
        if (StringUtils.isBlank(emails)) {
            return;
        }
        // 查询出要发送的信息
        StringBuilder sb = new StringBuilder();
        for (QueryNewsService service : queryNewsServices) {
            EnumNewsChannelType channelType = service.getChannelType();
            List<String> batchIdList = newsMailRecordService.queryHasNotMailedBatchId(channelType);
            StringBuilder stringBuilder = service.getNewsDetailStr(batchIdList);
            sb.append(stringBuilder);
            batchIdListMap.put(channelType.getCode(), batchIdList);
        }
        String mailText = sb.toString();
        if (StringUtils.isBlank(mailText)) {
            log.info("本次没有更新的新闻内容");
            return;
        }
        // 发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("新闻热搜磅");
        message.setFrom("yangwanhao126@126.com");
        message.setTo(emails);
        message.setSentDate(new Date());
        message.setText(mailText);
        javaMailSender.send(message);
        // 更新batchId为已发送
        Set<Map.Entry<String, List<String>>> entries = batchIdListMap.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            String channelType = entry.getKey();
            List<String> batchIdList = entry.getValue();
            newsMailRecordService.updateMailedWithBatchIdAndChannelType(channelType, batchIdList);
        }
    }


}

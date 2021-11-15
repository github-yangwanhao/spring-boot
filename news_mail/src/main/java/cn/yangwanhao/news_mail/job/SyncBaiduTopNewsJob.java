package cn.yangwanhao.news_mail.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.yangwanhao.base.common.support.ResponseMessage;
import cn.yangwanhao.base.common.util.IdUtils;
import cn.yangwanhao.news_mail.service.SyncNewsService;
import cn.yangwanhao.news_mail.utils.RedisLockKeyUtil;
import cn.yangwanhao.sdk.common.component.ILockComponent;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/14 19:38
 */
@Slf4j
@Component
public class SyncBaiduTopNewsJob {

    @Autowired
    @Qualifier("baiduSyncNewsServiceImp")
    private SyncNewsService syncNewsServices;
    @Autowired
    @Qualifier("redisLockComponent")
    private ILockComponent lockComponent;

    @Scheduled(cron = "0 0/30 * * * ?")
    // @Scheduled(cron = "0/10 * * * * ?")
    private void process(){
        try {
            ResponseMessage<Boolean> lockResult = lockComponent.tryLock(RedisLockKeyUtil.getSyncNewsBaiduKey(), 120L);
            if (!lockResult.doSuccess()) {
                return;
            }
            String batchId = IdUtils.getUuid();
            syncNewsServices.syncNewsToDatabase(batchId);
        } catch (Exception e) {
            log.error("同步百度消息发生异常,异常信息是:", e);
        } finally {
            lockComponent.unlock(RedisLockKeyUtil.getSyncNewsBaiduKey());
        }

    }

}

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;

import cn.yangwanhao.news_mail.NewsMailApplication;
import cn.yangwanhao.news_mail.job.SendNewsEmailJob;
import cn.yangwanhao.news_mail.pojo.BaiduNewsDto;
import cn.yangwanhao.news_mail.service.LoadNewsService;
import cn.yangwanhao.news_mail.service.QueryNewsService;
import cn.yangwanhao.news_mail.service.SyncNewsService;
import cn.yangwanhao.util.util.IdUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/13 14:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NewsMailApplication.class})
@Slf4j
public class LoadNewsServiceTest {

    @Autowired
    @Qualifier("baiduLoadNewsServiceImpl")
    private LoadNewsService<BaiduNewsDto> loadNewsService;

    @Autowired
    private SyncNewsService syncNewsService;
    @Autowired
    private SendNewsEmailJob sendNewsEmailJob;
    @Autowired
    private QueryNewsService baiduQueryNewsService;

    @Test
    public void testLoadBaiduNews() {
        List<BaiduNewsDto> baiduNewsDtos = loadNewsService.loadNews();
        System.out.println(JSONObject.toJSONString(baiduNewsDtos));
    }

    @Test
    public void testSyncBaiduNews() {
        Long snowFlakeId = IdUtils.getSnowFlakeId();
        syncNewsService.syncNewsToDatabase(String.valueOf(snowFlakeId));
    }

    @Test
    public void testSendMail() {
        sendNewsEmailJob.process();
    }

    @Test
    public void testDataMove() {
    }

    @Test
    public void query() {
        StringBuilder str = baiduQueryNewsService.getNewsDetailStr(
            Collections.singletonList("b54c85d0fbc84f34a2dac38257e875b6"));
        System.out.println(str.toString());
        System.out.println();
    }

}

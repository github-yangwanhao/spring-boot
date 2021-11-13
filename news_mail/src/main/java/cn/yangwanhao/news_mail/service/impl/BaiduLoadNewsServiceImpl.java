package cn.yangwanhao.news_mail.service.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import cn.yangwanhao.news_mail.pojo.BaiduNewsDto;
import cn.yangwanhao.news_mail.service.LoadNewsService;
import cn.yangwanhao.news_mail.utils.HttpClientUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/13 14:14
 */
@Slf4j
@Service
public class BaiduLoadNewsServiceImpl implements LoadNewsService<BaiduNewsDto> {

    @SneakyThrows
    @Override
    public List<BaiduNewsDto> loadNews() {
        List<BaiduNewsDto> result = Lists.newArrayList();
        AtomicInteger order = new AtomicInteger();

        String data = HttpClientUtil.getDataWithGetMethod("https://top.baidu.com/board?tab=realtime", "UTF-8");
        Document document = Jsoup.parse(data);

        Elements elements = document.getElementsByClass("category-wrap_iQLoo horizontal_1eKyQ");
        for (Element element : elements) {
            // title
            Element titleElement = element.getElementsByClass("c-single-text-ellipsis").get(0);
            Elements descElementsStyle1 = element.getElementsByClass("hot-desc_1m_jR small_Uvkd3 ellipsis_DupbZ");
            Elements descElementsStyle2 = element.getElementsByClass("hot-desc_1m_jR small_Uvkd3 ");
            String title = titleElement.text().trim();
            // desc
            String desc = "";
            if (descElementsStyle1.size() > 0) {
                Element descElement = descElementsStyle1.get(0);
                desc = descElement.text().trim();
            } else if (descElementsStyle2.size() > 0) {
                Element descElement = descElementsStyle2.get(0);
                desc = descElement.text().trim();
            }
            // order
            int orderNum = order.incrementAndGet();
            // image
            Elements img = element.select("img[src]");
            String imgUrl = img.attr("src");
            // url
            Elements urlElement = element.getElementsByClass("img-wrapper_29V76");
            String url = urlElement.attr("href");
            // score
            Elements scoreElement = element.getElementsByClass("hot-index_1Bl1a");
            String score = scoreElement.text().trim();

            BaiduNewsDto baiduNewsDto = new BaiduNewsDto();
            baiduNewsDto.setOrderNum(orderNum);
            baiduNewsDto.setTitle(title);
            baiduNewsDto.setDesc(desc);
            baiduNewsDto.setImageUrl(imgUrl);
            baiduNewsDto.setQueryUrl(url);
            baiduNewsDto.setHotScore(Long.valueOf(score));

            result.add(baiduNewsDto);
        }
        log.info("百度同步到的新闻集合是:{}", JSONObject.toJSONString(result));
        return result;
    }
}

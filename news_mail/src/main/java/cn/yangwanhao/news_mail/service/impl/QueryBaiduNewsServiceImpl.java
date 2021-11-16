package cn.yangwanhao.news_mail.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.yangwanhao.news_mail.dao.BaiduTopNewsMapper;
import cn.yangwanhao.news_mail.enums.EnumNewsChannelType;
import cn.yangwanhao.news_mail.model.BaiduTopNews;
import cn.yangwanhao.news_mail.model.BaiduTopNewsExample;
import cn.yangwanhao.news_mail.service.QueryNewsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/15 16:39
 */
@Slf4j
@Service
public class QueryBaiduNewsServiceImpl implements QueryNewsService {

    @Autowired
    private BaiduTopNewsMapper baiduTopNewsMapper;

    @Override
    public StringBuilder getNewsDetailStr(List<String> batchIdList) {
        BaiduTopNewsExample example = new BaiduTopNewsExample();
        BaiduTopNewsExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdIn(batchIdList);
        List<BaiduTopNews> baiduTopNews = baiduTopNewsMapper.selectByExample(example);
        return buildMailText(baiduTopNews);
    }

    @Override
    public EnumNewsChannelType getChannelType() {
        return EnumNewsChannelType.BAI_DU;
    }

    private StringBuilder buildMailText(List<BaiduTopNews> baiduTopNews) {
        if (CollectionUtils.isEmpty(baiduTopNews)) {
            return new StringBuilder();
        }
        StringBuilder sb = new StringBuilder();
        if (!CollectionUtils.isEmpty(baiduTopNews)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sb.append(separateLine)
                .append("\r\n");
            for (BaiduTopNews news : baiduTopNews) {
                sb.append("热搜榜排行第").append(news.getOrderNum()).append("名")
                    .append("\r\n")
                    .append("title : ")
                    .append(news.getTitle())
                    .append("\r\n")
                    .append("desc : ")
                    .append(news.getDetail())
                    .append("\r\n")
                    .append("queryUrl : ")
                    .append(news.getQueryUrl())
                    .append("\r\n")
                    .append("hotScore : ")
                    .append(news.getHotScore())
                    .append("\r\n")
                    .append("同步时间 : ")
                    .append(simpleDateFormat.format(news.getCreateTime()))
                    .append("\r\n")
                    .append(separateLine)
                    .append("\r\n");
            }
        }
        return sb;
    }
}

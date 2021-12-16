package cn.yangwanhao.news_mail.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yangwanhao.news_mail.dao.BaiduTopNewsAllMapper;
import cn.yangwanhao.news_mail.dao.BaiduTopNewsMapper;
import cn.yangwanhao.news_mail.enums.EnumNewsChannelType;
import cn.yangwanhao.news_mail.model.BaiduTopNews;
import cn.yangwanhao.news_mail.model.BaiduTopNewsAll;
import cn.yangwanhao.news_mail.model.BaiduTopNewsExample;
import cn.yangwanhao.news_mail.pojo.BaiduNewsDto;
import cn.yangwanhao.news_mail.service.LoadNewsService;
import cn.yangwanhao.news_mail.service.NewsMailRecordService;
import cn.yangwanhao.news_mail.service.SyncNewsService;
import cn.yangwanhao.util.util.DateUtils;
import cn.yangwanhao.util.util.IdUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/13 14:47
 */
@Slf4j
@Service
public class BaiduSyncNewsServiceImp implements SyncNewsService {

    @Autowired
    @Qualifier("baiduLoadNewsServiceImpl")
    private LoadNewsService<BaiduNewsDto> loadNewsService;

    @Autowired
    private NewsMailRecordService newsMailRecordService;

    @Autowired
    private BaiduTopNewsMapper baiduTopNewsMapper;
    @Autowired
    private BaiduTopNewsAllMapper baiduTopNewsAllMapper;

    @Override
    public void syncNewsToDatabase(String batchId) {
        // 计算是否同步增量的
        AtomicInteger insertCount = new AtomicInteger(0);
        // 查询过去30天的百度头条 基本上不会有头条活过30天
        BaiduTopNewsExample example = new BaiduTopNewsExample();
        BaiduTopNewsExample.Criteria criteria = example.createCriteria();
        criteria.andCreateTimeGreaterThanOrEqualTo(DateUtils.getPastDate(new Date(), 30));
        List<BaiduTopNews> baiduTopNewsList = baiduTopNewsMapper.selectByExample(example);
        // 只获取title
        List<String> titleList = baiduTopNewsList.stream()
            .map(BaiduTopNews::getTitle)
            .distinct()
            .collect(Collectors.toList());
        // 获取当前的百度头条
        List<BaiduNewsDto> baiduNewsDtoList = loadNewsService.loadNews();
        // 向数据库同步
        for (BaiduNewsDto baiduNewsDto : baiduNewsDtoList) {
            // 向baidu_top_news_all表同步 全部同步
            BaiduTopNewsAll baiduTopNewsAll = buildBaiduNewsAll(baiduNewsDto, batchId);
            baiduTopNewsAllMapper.insert(baiduTopNewsAll);
            if (titleList.contains(baiduNewsDto.getTitle())) {
                log.info("title[{}]已经在数据库中存在,不再同步", baiduNewsDto.getTitle());
                continue;
            }
            // 向 baidu_top_news 表同步 只同步增量的
            BaiduTopNews baiduTopNews = buildBaiduNews(baiduNewsDto, batchId);
            baiduTopNewsMapper.insert(baiduTopNews);
            insertCount.incrementAndGet();
        }
        if (insertCount.get() != 0) {
            newsMailRecordService.initOneRecord(batchId, getChannelType());
        }
    }

    @Override
    public EnumNewsChannelType getChannelType() {
        return EnumNewsChannelType.BAI_DU;
    }

    private BaiduTopNews buildBaiduNews(BaiduNewsDto baiduNewsDto, String batchId) {
        BaiduTopNews model = new BaiduTopNews();
        model.setId(String.valueOf(IdUtils.getSnowFlakeId()));
        model.setBatchId(batchId);
        model.setOrderNum(baiduNewsDto.getOrderNum());
        model.setTitle(baiduNewsDto.getTitle());
        model.setImageUrl(baiduNewsDto.getImageUrl());
        model.setDetail(baiduNewsDto.getDesc());
        model.setQueryUrl(baiduNewsDto.getQueryUrl());
        model.setHotScore(baiduNewsDto.getHotScore());
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return model;
    }

    private BaiduTopNewsAll buildBaiduNewsAll(BaiduNewsDto baiduNewsDto, String batchId) {
        BaiduTopNewsAll model = new BaiduTopNewsAll();
        model.setId(String.valueOf(IdUtils.getSnowFlakeId()));
        model.setBatchId(batchId);
        model.setOrderNum(baiduNewsDto.getOrderNum());
        model.setTitle(baiduNewsDto.getTitle());
        model.setImageUrl(baiduNewsDto.getImageUrl());
        model.setDetail(baiduNewsDto.getDesc());
        model.setQueryUrl(baiduNewsDto.getQueryUrl());
        model.setHotScore(baiduNewsDto.getHotScore());
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return model;
    }
}

package cn.yangwanhao.news_mail.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yangwanhao.base.common.util.IdUtils;
import cn.yangwanhao.news_mail.dao.BaiduTopNewsMapper;
import cn.yangwanhao.news_mail.model.BaiduTopNews;
import cn.yangwanhao.news_mail.pojo.BaiduNewsDto;
import cn.yangwanhao.news_mail.service.LoadNewsService;
import cn.yangwanhao.news_mail.service.SyncNewsService;
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
    private BaiduTopNewsMapper baiduTopNewsMapper;

    @Override
    public void syncNewsToDatabase(String batchId) {
        List<BaiduNewsDto> baiduNewsDtoList = loadNewsService.loadNews();
        for (BaiduNewsDto baiduNewsDto : baiduNewsDtoList) {
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
            baiduTopNewsMapper.insert(model);
        }
    }
}

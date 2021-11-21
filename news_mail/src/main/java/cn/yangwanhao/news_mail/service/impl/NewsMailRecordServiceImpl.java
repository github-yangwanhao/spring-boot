package cn.yangwanhao.news_mail.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yangwanhao.base.enums.EnumBoolean;
import cn.yangwanhao.news_mail.dao.NewsMailRecordMapper;
import cn.yangwanhao.news_mail.enums.EnumNewsChannelType;
import cn.yangwanhao.news_mail.model.NewsMailRecord;
import cn.yangwanhao.news_mail.model.NewsMailRecordExample;
import cn.yangwanhao.news_mail.service.NewsMailRecordService;
import cn.yangwanhao.util.IdUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/15 17:28
 */
@Slf4j
@Service
public class NewsMailRecordServiceImpl implements NewsMailRecordService {

    @Autowired
    private NewsMailRecordMapper newsMailRecordMapper;

    @Override
    public List<String> queryHasNotMailedBatchId(EnumNewsChannelType channelType) {
        NewsMailRecordExample example = new NewsMailRecordExample();
        NewsMailRecordExample.Criteria criteria = example.createCriteria();
        criteria.andChannelTypeEqualTo(channelType.getCode());
        criteria.andHasMailEqualTo(EnumBoolean.FALSE.getKey());
        List<NewsMailRecord> newsMailRecords = newsMailRecordMapper.selectByExample(example);
        return newsMailRecords.stream()
            .map(NewsMailRecord::getBatchId)
            .collect(Collectors.toList());
    }

    @Override
    public void updateMailedWithBatchIdAndChannelType(String channelType, List<String> batchIdList) {

        NewsMailRecordExample example = new NewsMailRecordExample();
        NewsMailRecordExample.Criteria criteria = example.createCriteria();
        criteria.andChannelTypeEqualTo(channelType);
        criteria.andBatchIdIn(batchIdList);

        NewsMailRecord record = new NewsMailRecord();
        record.setHasMail(EnumBoolean.TRUE.getKey());

        newsMailRecordMapper.updateByExampleSelective(record, example);
    }

    @Override
    public void initOneRecord(String batchId, EnumNewsChannelType channelType) {
        NewsMailRecord record = new NewsMailRecord();
        record.setId(IdUtils.getSnowFlakeIdString());
        record.setBatchId(batchId);
        record.setChannelType(channelType.getCode());
        record.setHasMail(EnumBoolean.FALSE.getKey());
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        newsMailRecordMapper.insertSelective(record);
    }

}

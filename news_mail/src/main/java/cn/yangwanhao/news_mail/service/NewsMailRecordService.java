package cn.yangwanhao.news_mail.service;

import java.util.List;

import cn.yangwanhao.news_mail.enums.EnumNewsChannelType;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/15 17:28
 */
public interface NewsMailRecordService {

    /**
     * 根据渠道类型查询没有发送邮件的batchId
     * @param channelType 渠道类型
     * @return batchIdList
     */
    List<String> queryHasNotMailedBatchId(EnumNewsChannelType channelType);

    /**
     * 根据batchId和channelType更新状态为已发送邮件
     * @param channelType 渠道类型
     * @param batchIdList batchIdList
     */
    void updateMailedWithBatchIdAndChannelType(String channelType, List<String> batchIdList);

    /**
     * 初始化一条数据
     * @param batchId batchId
     * @param channelType 渠道类型
     * @return
     */
    void initOneRecord(String batchId, EnumNewsChannelType channelType);


}

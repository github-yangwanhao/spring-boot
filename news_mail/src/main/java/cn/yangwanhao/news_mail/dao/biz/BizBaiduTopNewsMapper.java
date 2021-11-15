package cn.yangwanhao.news_mail.dao.biz;

import org.springframework.stereotype.Repository;

@Repository
public interface BizBaiduTopNewsMapper {
    /**
     * 获取最后一个batchId
     * @return batchId
     */
    String getLastBatchId();

}
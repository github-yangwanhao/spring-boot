package cn.yangwanhao.news_mail.service;

import java.util.List;

import cn.yangwanhao.news_mail.enums.EnumNewsChannelType;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/15 16:38
 */
public abstract class QueryNewsService {

    private static final String SEPARATE_LINE = "---------------------------------------------%s---------------------------------------------";

    protected String getSeparateLine() {
        return String.format(SEPARATE_LINE, getChannelType().getDescription());
    }

    /**
     * 获取新闻列表的字符串
     * @return str
     */
    public abstract StringBuilder getNewsDetailStr(List<String> batchIdList);

    /**
     * 获取渠道类型
     * @return 渠道类型
     */
    public abstract EnumNewsChannelType getChannelType();
}

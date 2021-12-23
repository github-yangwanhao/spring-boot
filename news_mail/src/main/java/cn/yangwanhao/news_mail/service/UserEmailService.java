package cn.yangwanhao.news_mail.service;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/15 16:50
 */
public interface UserEmailService {

    /**
     * 获取邮箱数组
     * @return 邮箱数组
     */
    String[] getEmailAddressListStr();
}

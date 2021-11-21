package cn.yangwanhao.news_mail.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.yangwanhao.base.enums.EnumBoolean;
import cn.yangwanhao.news_mail.dao.UserEmailInfoMapper;
import cn.yangwanhao.news_mail.model.UserEmailInfo;
import cn.yangwanhao.news_mail.model.UserEmailInfoExample;
import cn.yangwanhao.news_mail.service.UserEmailService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/15 16:50
 */
@Slf4j
@Service
public class UserEmailServiceImpl implements UserEmailService {

    @Autowired
    private UserEmailInfoMapper userEmailInfoMapper;

    @Override
    public String getEmailAddressListStr() {
        UserEmailInfoExample example = new UserEmailInfoExample();
        UserEmailInfoExample.Criteria criteria = example.createCriteria();
        criteria.andEmailStatusEqualTo(EnumBoolean.TRUE.getKey());
        List<UserEmailInfo> userEmailInfos = userEmailInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userEmailInfos)) {
            return null;
        }
        String emails = userEmailInfos.stream()
            .map(UserEmailInfo::getEmail)
            .collect(Collectors.joining(","));
        log.info("本次要发送的email有[{}]", emails);
        return emails;
    }
}

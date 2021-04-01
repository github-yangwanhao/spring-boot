package cn.yangwanhao.boot.exception.retry.demo.service;

import cn.yangwanhao.boot.exception.retry.demo.dto.OperateDto;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/1/14 10:18
 */
public interface IOperateService {

    Integer add(OperateDto dto);
}

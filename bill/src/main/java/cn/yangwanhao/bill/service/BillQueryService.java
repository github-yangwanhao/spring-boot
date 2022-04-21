package cn.yangwanhao.bill.service;

import com.github.pagehelper.PageInfo;

import cn.yangwanhao.bill.dto.QueryBillDto;
import cn.yangwanhao.bill.vo.BillListVo;

/**
 * 账单查询服务
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2022/2/9 14:14
 */
public interface BillQueryService {

    /**
     * 根据条件查询账单列表
     * @param dto dto
     * @return pageInfo
     */
    PageInfo<BillListVo> queryBillList(QueryBillDto dto);
}

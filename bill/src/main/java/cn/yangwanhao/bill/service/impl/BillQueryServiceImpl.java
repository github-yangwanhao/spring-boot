package cn.yangwanhao.bill.service.impl;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.yangwanhao.bill.dto.QueryBillDto;
import cn.yangwanhao.bill.service.BillQueryService;
import cn.yangwanhao.bill.vo.BillListVo;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/2/9 14:24
 */
@Service
public class BillQueryServiceImpl implements BillQueryService {

    @Override
    public PageInfo<BillListVo> queryBillList(QueryBillDto dto) {
        return null;
    }
}

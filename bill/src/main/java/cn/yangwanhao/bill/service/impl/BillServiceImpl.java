package cn.yangwanhao.bill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yangwanhao.bill.dao.BillMapper;
import cn.yangwanhao.bill.model.Bill;
import cn.yangwanhao.bill.service.BillService;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/1/11 16:14
 */
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchInsertBill(List<Bill> bills) {
        for (Bill bill : bills) {
            billMapper.insert(bill);
        }
    }
}

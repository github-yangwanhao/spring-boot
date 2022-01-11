package cn.yangwanhao.bill.service;

import java.util.List;

import cn.yangwanhao.bill.model.Bill;

/**
 * 账单接口
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2022/1/11 16:13
 */
public interface BillService {

    /**
     * 批量添加账单
     * @return
     */
    void batchInsertBill(List<Bill> bills);

}

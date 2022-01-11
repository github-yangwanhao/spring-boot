package cn.yangwanhao.bill.service;

import java.io.File;

/**
 * 账单导入接口
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2022/1/11 16:08
 */
public interface BillImportService {

    /**
     * 导入账单文件
     * @param file 账单文件
     */
    void importBill(File file);
}

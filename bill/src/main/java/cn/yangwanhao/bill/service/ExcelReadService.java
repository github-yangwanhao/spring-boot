package cn.yangwanhao.bill.service;

import java.io.InputStream;
import java.util.List;


/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/28 14:23
 */
public interface ExcelReadService<E> {

    List<E> readExcel(InputStream inputStream);
}

package cn.yangwanhao.bill.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.excel.EasyExcel;

import cn.yangwanhao.bill.easyexcel.dto.BillDto;
import cn.yangwanhao.bill.easyexcel.listener.BillReadExcelListener;
import cn.yangwanhao.bill.service.ExcelReadService;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/28 14:25
 */
@Service
public class BillExcelReadServiceImpl implements ExcelReadService<BillDto> {
    @Override
    public List<BillDto> readExcel(InputStream inputStream) {
        BillReadExcelListener billReadExcelListener = new BillReadExcelListener();
        EasyExcel.read(inputStream,BillDto.class, billReadExcelListener)
            .sheet("Sheet1")
            .autoTrim(true)
            .headRowNumber(1)
            .doRead();
        return billReadExcelListener.getList();
    }
}

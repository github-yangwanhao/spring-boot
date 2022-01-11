package cn.yangwanhao.bill.easyexcel.listener;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import cn.yangwanhao.bill.easyexcel.dto.BillDto;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/28 14:44
 */
public class BillReadExcelListener extends AnalysisEventListener<BillDto> {

    private List<BillDto> list = new ArrayList<>();

    public List<BillDto> getList() {
        return list;
    }
    @Override
    public void invoke(BillDto data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}

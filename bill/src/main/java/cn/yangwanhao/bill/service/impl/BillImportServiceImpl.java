package cn.yangwanhao.bill.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.yangwanhao.bill.easyexcel.dto.BillDto;
import cn.yangwanhao.bill.enums.EnumConsumptionCategory;
import cn.yangwanhao.bill.enums.EnumPayChannel;
import cn.yangwanhao.bill.model.Bill;
import cn.yangwanhao.bill.model.FileImportHistory;
import cn.yangwanhao.bill.service.BillImportService;
import cn.yangwanhao.bill.service.BillService;
import cn.yangwanhao.bill.service.ExcelReadService;
import cn.yangwanhao.bill.service.FileImportHistoryService;
import cn.yangwanhao.model.constant.DatePattern;
import cn.yangwanhao.util.util.CollectionUtils;
import cn.yangwanhao.util.util.DateUtils;
import cn.yangwanhao.util.util.IdUtils;
import lombok.SneakyThrows;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/1/11 16:09
 */
@Service
public class BillImportServiceImpl implements BillImportService {

    @Autowired
    private FileImportHistoryService fileImportHistoryService;
    @Autowired
    private ExcelReadService<BillDto> excelReadService;
    @Autowired
    private BillService billService;

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public void importBill(File file) {
        String fileName = file.getName();
        FileImportHistory fileImportHistory = fileImportHistoryService.queryByNameMd5(fileName, "");
        if (fileImportHistory != null) {
            return;
        }
        InputStream md5InputStream = new FileInputStream(file);
        String md5 = DigestUtils.md5Hex(md5InputStream);
        InputStream excelReadInputStream = new FileInputStream(file);
        List<BillDto> billList = excelReadService.readExcel(excelReadInputStream);
        List<Bill> bills = convertBillDtoList(billList);
        fileImportHistoryService.insertHistory(fileName, billList.size(), md5);
        billService.batchInsertBill(bills);
    }

    private List<Bill> convertBillDtoList(List<BillDto> dtoList) {
        List<Bill> bills = Lists.newArrayList();
        if (CollectionUtils.isEmpty(dtoList)) {
            return bills;
        }
        for (BillDto dto : dtoList) {
            bills.add(convertBillDto(dto));
        }
        return bills;
    }

    private Bill convertBillDto(BillDto dto) {
        Bill bill = new Bill();
        bill.setId(IdUtils.getSnowFlakeIdString());
        bill.setHappenDate(DateUtils.getDate(dto.getDate(), DatePattern.DATE_FORMAT_2));
        bill.setRemark(dto.getRemark());
        EnumConsumptionCategory category = EnumConsumptionCategory.findByDesc(dto.getCategory());
        bill.setCategory(category.getCode());
        EnumPayChannel payChannel = EnumPayChannel.findByDesc(dto.getPayChannel());
        bill.setCategoryName(category.getDescription());
        bill.setPayChannel(payChannel.getCode());
        bill.setPayChannelName(payChannel.getDescription());
        bill.setAmount(new BigDecimal(dto.getAmount()));
        bill.setCreateTime(new Date());
        bill.setUpdateTime(new Date());
        return bill;
    }
}

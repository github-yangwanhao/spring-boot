package cn.yangwanhao.bill.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import cn.yangwanhao.bill.easyexcel.dto.BillDto;
import cn.yangwanhao.bill.service.ExcelReadService;
import cn.yangwanhao.bill.service.impl.BillExcelReadServiceImpl;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/1/11 16:28
 */
public class BillReadTest {

    @Test
    public void testReadDir() throws IOException {
        String filePath = "D:\\账单\\2021";
        File rootDir = new File(filePath);
        if (rootDir.isDirectory()) {
            File[] files = rootDir.listFiles();
            assert files != null;
            ExcelReadService<BillDto> excelReadService = new BillExcelReadServiceImpl();
            for (File file : files) {
                InputStream inputStream = new FileInputStream(file);
                String fileName = file.getName();
                List<BillDto> billDtos = excelReadService.readExcel(inputStream);
                BigDecimal total = new BigDecimal(0);
                for (BillDto bill : billDtos) {
                    total = total.add(new BigDecimal(bill.getAmount()));
                }
                System.out.println(billDtos.size());
                System.out.println(total);
                System.out.println(fileName);
                InputStream md5Stream = new FileInputStream(file);
                String md5 = DigestUtils.md5Hex(md5Stream);
                System.out.println(md5);
                System.out.println("---------------------------------------");
                assert total.toString().length() <= 7;
                inputStream.close();
                md5Stream.close();
            }
        }
    }
}

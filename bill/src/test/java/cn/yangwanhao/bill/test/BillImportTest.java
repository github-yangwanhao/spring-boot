package cn.yangwanhao.bill.test;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.yangwanhao.bill.BillApplication;
import cn.yangwanhao.bill.service.BillImportService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/1/11 16:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BillApplication.class})
@Slf4j
public class BillImportTest {

    @Autowired
    private BillImportService billImportService;

    @Test
    public void importBill() {
        String filePath = "D:\\账单\\2021";
        File rootDir = new File(filePath);
        if (rootDir.isDirectory()) {
            File[] files = rootDir.listFiles();
            assert files != null;
            for (File file : files) {
                billImportService.importBill(file);
            }
        }
    }
}

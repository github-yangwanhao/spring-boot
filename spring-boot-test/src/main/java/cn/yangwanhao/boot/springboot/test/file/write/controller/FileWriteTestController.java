package cn.yangwanhao.boot.springboot.test.file.write.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yangwanhao.boot.springboot.test.file.write.service.FileWriteTestService;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/4/15 20:04
 */
@RestController
public class FileWriteTestController {

    @Autowired
    private FileWriteTestService fileWriteTestService;

    @GetMapping("/file/test/init")
    public void initData() {
        fileWriteTestService.initData();
    }

    @GetMapping("/file/test/write")
    public void writeFile() {
        fileWriteTestService.writeFile();
    }

    @GetMapping("/file/test/read")
    public void readFile() {
        fileWriteTestService.readFile();
    }

}

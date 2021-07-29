package cn.yangwanhao.boot.springboot.test.file.write.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yangwanhao.boot.springboot.test.file.write.dao.FileWriteTestMapper;
import cn.yangwanhao.boot.springboot.test.file.write.pojo.FileWriteTest;
import cn.yangwanhao.boot.springboot.test.file.write.pojo.FileWriteTestExample;
import cn.yangwanhao.boot.springboot.test.log.advice.annotation.PrintExecuteTimes;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/4/15 19:40
 */
@Slf4j
@Service
public class FileWriteTestServiceImpl implements FileWriteTestService {

    @Autowired
    private FileWriteTestMapper mapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final int SIZE = 1000000;
    private static final String FILE_LOCATION = "D:\\java_file\\java\\test\\USER.txt";
    private static final String TRUNCATE_TABLE_SQL = "TRUNCATE TABLE file_write_test";
    private static final Integer MAX_PAGE_SIZE = 10000;

    @Override
    @PrintExecuteTimes
    @Transactional(rollbackFor = Exception.class)
    public void initData() {
        List<FileWriteTest> list = new ArrayList<>(SIZE);
        for (int i = 1; i < SIZE + 1; i++) {
            FileWriteTest bean = new FileWriteTest();
            bean.setId(StringUtils.leftPad(String.valueOf(i), 7, "0"));
            bean.setName("name" + StringUtils.leftPad(String.valueOf(i), 7, "0"));
            bean.setPhone(StringUtils.leftPad(String.valueOf(i), 11, "0"));
            bean.setEmail("yangwanhao" + StringUtils.leftPad(String.valueOf(i), 7, "0") + "@126.com");
            bean.setAddress("河南省" + StringUtils.leftPad(String.valueOf(i), 7, "0"));
            list.add(bean);
        }
        mapper.insertBatch(list);
    }

    @Override
    @PrintExecuteTimes
    public void writeFile() {
        List<FileWriteTest> list = mapper.selectByExample(new FileWriteTestExample());
        //以文件名创建文件变量
        File filename = new File(FILE_LOCATION);
        // 先删除,确保追加文件内容时是空文件
        if (filename.exists()) {
            boolean deleteResult = filename.delete();
            log.info("原文件删除是否成功:{}", deleteResult);
        }
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try {
            boolean newFileResult = filename.createNewFile();
            log.info("创建文件是否成功:{}", newFileResult);
            if (!newFileResult) {
                throw new RuntimeException("创建文件失败");
            }
            fileWriter = new FileWriter(filename);
            //数据库中只包含文字信息，建议使用bufferedWriter写入文件，更快速。
            bufferedWriter = new BufferedWriter(fileWriter);
            if (!list.isEmpty()) {
                for (FileWriteTest bean : list) {
                    bufferedWriter.write(bean.toString());
                    bufferedWriter.newLine();
                }
                //数据写完用flush方法，不然数据写不出来
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(bufferedWriter);
            IOUtils.closeQuietly(fileWriter);
        }
    }

    @Override
    @PrintExecuteTimes
    @Transactional(rollbackFor = Exception.class)
    public void readFile() {
        Connection connection = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        int count;
        try {
            count = mapper.countByExample(new FileWriteTestExample());
            if (count != 0) {
                // 先清空库
                connection = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(TRUNCATE_TABLE_SQL);
                preparedStatement.executeUpdate();
            }
            List<FileWriteTest> list = new ArrayList<>(count);
            // 读取文件
            File file = new File(FILE_LOCATION);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String lineStr;
            lineStr = bufferedReader.readLine();
            while (StringUtils.isNotBlank(lineStr)) {
                list.add(buildBean(lineStr));
                lineStr = bufferedReader.readLine();
            }
            mapper.insertBatch(list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (fileReader != null) {
                    IOUtils.closeQuietly(fileReader);
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private FileWriteTest buildBean(String str) {
        FileWriteTest fileWriteTest = new FileWriteTest();
        String[] array = str.split("\\|");
        if (array.length != 5) {
            throw new RuntimeException("切割出的字符串长度不符合要求");
        }
        fileWriteTest.setId(array[0]);
        fileWriteTest.setName(array[1]);
        fileWriteTest.setPhone(array[2]);
        fileWriteTest.setEmail(array[3]);
        fileWriteTest.setAddress(array[4]);
        return fileWriteTest;
    }

}

package cn.yangwanhao.boot.springboot.test.file.write.service;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/4/15 19:39
 */
public interface FileWriteTestService {

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 读取数据库，写入文件
     */
    void writeFile();

    /**
     * 读取文件,写入数据库
     */
    void readFile();

}

package cn.yangwanhao.bill.service;

import cn.yangwanhao.bill.model.FileImportHistory;

/**
 * 文件导入记录接口
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2022/1/11 15:28
 */
public interface FileImportHistoryService {

    /**
     * 保存文件记录
     * @param fileName 文件名
     * @param fileRow 文件总行数
     * @param fileMd5 文件md5
     */
    void insertHistory(String fileName, Integer fileRow, String fileMd5);

    /**
     * 查询文件记录
     * @param fileName 文件名
     * @param fileMd5 文件MD5
     * @return 记录
     */
    FileImportHistory queryByNameMd5(String fileName, String fileMd5);
}

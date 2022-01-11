package cn.yangwanhao.bill.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yangwanhao.bill.dao.FileImportHistoryMapper;
import cn.yangwanhao.bill.model.FileImportHistory;
import cn.yangwanhao.bill.model.FileImportHistoryExample;
import cn.yangwanhao.bill.service.FileImportHistoryService;
import cn.yangwanhao.util.util.CollectionUtils;
import cn.yangwanhao.util.util.IdUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/1/11 15:29
 */
@Slf4j
@Service
public class FileImportHistoryServiceImpl implements FileImportHistoryService {

    @Autowired
    private FileImportHistoryMapper fileImportHistoryMapper;

    @Override
    public void insertHistory(String fileName, Integer fileRow, String fileMd5) {
        FileImportHistory record = new FileImportHistory();
        record.setId(IdUtils.getSnowFlakeIdString());
        record.setFileName(fileName);
        record.setFileRow(fileRow);
        record.setFileMd5(fileMd5);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        fileImportHistoryMapper.insert(record);
    }

    @Override
    public FileImportHistory queryByNameMd5(String fileName, String fileMd5) {
        FileImportHistoryExample example = new FileImportHistoryExample();
        FileImportHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andFileNameEqualTo(fileName);
        List<FileImportHistory> fileImportHistories = fileImportHistoryMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(fileImportHistories)) {
            return null;
        }
        return fileImportHistories.get(0);
    }
}

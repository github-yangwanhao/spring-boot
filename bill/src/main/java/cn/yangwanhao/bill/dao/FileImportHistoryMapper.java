package cn.yangwanhao.bill.dao;

import cn.yangwanhao.bill.model.FileImportHistory;
import cn.yangwanhao.bill.model.FileImportHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileImportHistoryMapper {
    int countByExample(FileImportHistoryExample example);

    int deleteByExample(FileImportHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(FileImportHistory record);

    int insertSelective(FileImportHistory record);

    List<FileImportHistory> selectByExample(FileImportHistoryExample example);

    FileImportHistory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FileImportHistory record, @Param("example") FileImportHistoryExample example);

    int updateByExample(@Param("record") FileImportHistory record, @Param("example") FileImportHistoryExample example);

    int updateByPrimaryKeySelective(FileImportHistory record);

    int updateByPrimaryKey(FileImportHistory record);
}
package cn.yangwanhao.boot.springboot.test.file.write.dao;

import cn.yangwanhao.boot.springboot.test.file.write.pojo.FileWriteTest;
import cn.yangwanhao.boot.springboot.test.file.write.pojo.FileWriteTestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileWriteTestMapper {
    int countByExample(FileWriteTestExample example);

    int deleteByExample(FileWriteTestExample example);

    int deleteByPrimaryKey(String id);

    int insert(FileWriteTest record);

    int insertSelective(FileWriteTest record);

    int insertBatch(List<FileWriteTest> list);

    List<FileWriteTest> selectByExample(FileWriteTestExample example);

    FileWriteTest selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FileWriteTest record, @Param("example") FileWriteTestExample example);

    int updateByExample(@Param("record") FileWriteTest record, @Param("example") FileWriteTestExample example);

    int updateByPrimaryKeySelective(FileWriteTest record);

    int updateByPrimaryKey(FileWriteTest record);
}
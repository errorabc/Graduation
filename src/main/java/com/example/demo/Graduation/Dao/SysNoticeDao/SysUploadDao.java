package com.example.demo.Graduation.Dao.SysNoticeDao;

import com.example.demo.Graduation.entity.SysUploadEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUploadDao {

    List<SysUploadEntity> FindAllUploadInfo(@Param("name") String name);

    boolean AddUploadReource(SysUploadEntity sysUploadEntity);


    SysUploadEntity IdFindUploadInfo(@Param("id") String id);


    boolean DeleteUploadReource(@Param("id") String id);


    boolean UpdateUploadReource(@Param("id") String id,@Param("name") String name,@Param("url") String url);
}

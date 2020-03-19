package com.example.demo.Graduation.Dao.LogDao;

import com.example.demo.Graduation.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogDao {

    void saveSysLog(LogEntity logEntity);


    List<LogEntity> FindSysLog(@Param("starttime") String starttime, @Param("endtime") String endtime, @Param("username") String username);

    LogEntity IdFindLog(@Param("id") String id);


}

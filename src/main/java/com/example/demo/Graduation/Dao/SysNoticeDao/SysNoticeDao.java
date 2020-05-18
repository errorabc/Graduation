package com.example.demo.Graduation.Dao.SysNoticeDao;

import com.example.demo.Graduation.entity.SysNoticeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.function.BinaryOperator;

@Mapper
public interface SysNoticeDao {
    //查询
    List<SysNoticeEntity> FindAllSysNoticeInfo(@Param("name") String name);


    //添加
    boolean AddSnotice(SysNoticeEntity sysNoticeEntity);

    //Id查询公告信息
    SysNoticeEntity IdFindSysNoticeInfo(@Param("id") String id);


    //修改公告信息
    boolean UpdateNotice(SysNoticeEntity sysNoticeEntity);

    //删除公告
    boolean DeleteNotice(@Param("id") String id);


}


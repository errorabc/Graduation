package com.example.demo.Graduation.Dao.MemberDao;

import com.example.demo.Graduation.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ActivityDao {
    //查询
    List<Activity> FinAllActivityInfo(@Param("name") String name);

    //停止活动
    boolean UpdateActivityStatus(@Param("id") String id);

    //活动名查询信息
    Activity NameFindActivityInfo(@Param("name") String name);

    //ID查询信息
    Activity IdFindActivityInfo(@Param("id") String id);

    //添加活动
    boolean AddActivity(Activity activity);

    //删除活动
    boolean DeleteActivity(@Param("id") String id);

    //修改活动
    boolean UpdateActivity(Activity activity);


    //查询当前时间内的活动信息
    List<Activity> FindUnderwayActivity(@Param("time")String time);


}

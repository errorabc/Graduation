package com.example.demo.Graduation.Dao.OderDao;

import com.example.demo.Graduation.entity.OderEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OderDao {
    //添加总订单
    boolean AddOder(OderEntity oderEntity);

}

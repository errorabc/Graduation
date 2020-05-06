package com.example.demo.Graduation.Dao.OderDao;

import com.example.demo.Graduation.entity.OderItemEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OderItemDao {

    boolean AddOderItem(OderItemEntity oderItemEntity);

}
